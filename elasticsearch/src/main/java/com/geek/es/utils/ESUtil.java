package com.geek.es.utils;

import com.geek.es.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description :
 */
@Slf4j
@Component
public class ESUtil {

//    @Autowired
//    private static TransportClient transportClient;

    @Autowired
    private TransportClient client;

    @Autowired
    private ApplicationContext applicationContext;

    private static TransportClient transportClient;

    @PostConstruct
    public void init(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if(beanDefinitionName.equals("transportClient")){
                log.warn("transportClient ：{}",true);
            }
        }
        transportClient = this.client;
    }

    // 封装统一返回map对象
    public static Map<String,Object> resultMap = new HashMap<>(2);

    /**
     * @param index index名称
     * @return 创建index
     */
    public static Map<String,Object> createIndex(String index){
        // 如果index存在，则直接返回
        if(isIndexExist(index)){
            resultMap.put("code", StatusEnum.EXIST.getCode());
            resultMap.put("msg", StatusEnum.EXIST.getMsg());
            return resultMap;
        }
        CreateIndexResponse createIndexResponse = transportClient.admin().indices().prepareCreate(index).execute().actionGet();
        // 再次判断index是否创建成功
        return operationMap(createIndexResponse.isAcknowledged());
    }

    /**
     * @param index index名称
     * @return 删除index
     */
    public static Map<String,Object> deleteIndex(String index){
        if(!isIndexExist(index)){
            resultMap.put("code", StatusEnum.NOT_EXIST.getCode());
            resultMap.put("msg", StatusEnum.NOT_EXIST.getMsg());
            return resultMap;
        }
        // 再次判断是否删除index成功
        DeleteIndexResponse deleteIndexResponse = transportClient.admin().indices().prepareDelete(index).execute().actionGet();
        return operationMap(deleteIndexResponse.isAcknowledged());
    }

    /**
     * @param index index名称
     * @return 判断index是否存在
     */
    public static Boolean isIndexExist(String index){
        IndicesExistsResponse indicesExistsResponse = transportClient.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
        return indicesExistsResponse.isExists();
    }

    /**
     * @param index index名称
     * @param type type名称
     * @return 根据index名称和type名称判断 type是否存在
     */
    public static Boolean isTypeExist(String index,String type){
        return isIndexExist(index) && transportClient.admin().indices().prepareTypesExists(type).execute().actionGet().isExists();
    }

    /**
     * @param index 添加数据所在的index
     * @param type 添加数据所在的type
     * @param id 数据id
     * @param data 添加的数据
     * @return 操作状态
     */
    public static Map<String,Object> addData(String index,String type,String id,Map<String,Object> data){
        IndexResponse indexResponse = transportClient.prepareIndex(index, type, id).setSource(data).get();
        String status = indexResponse.status().toString();
        return operationMap("OK".equals(status.toUpperCase()));
    }

    /**
     * 数据id为UUID随机生成
     * @param index 添加数据所在的index
     * @param type 添加数据所在的type
     * @param data 添加的数据
     * @return 操作状态
     */
    public static Map<String,Object> addData(String index, String type, Map<String, Object> data){
        return addData(index,type, UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),data);
    }

    /**
     * @param index
     * @param type
     * @param id
     * @return 通过id删除数据
     */
    public static Map<String,Object> deleteDataById(String index,String type,String id){
        DeleteResponse deleteResponse = transportClient.prepareDelete(index, type, id).execute().actionGet();
        return operationMap("OK".equals(deleteResponse.status().toString().toUpperCase()));
    }

    /**
     * @param index
     * @param type
     * @param id
     * @param data
     * @return 通过id修改数据
     */
    public static Map<String,Object> updateDataById(String index,String type,String id,Map<String,Object> data){
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(data);
        UpdateResponse updateResponse = transportClient.update(updateRequest).actionGet();
        return operationMap("OK".equals(updateResponse.status().toString().toUpperCase()));
    }

    /**
     * @param index
     * @param type
     * @param id
     * @param fields
     * @return 根据id和字段查询数据
     */
    public static Map<String,Object> selectDataById(String index,String type,String id,String fields){
        GetRequestBuilder getRequestBuilder = transportClient.prepareGet(index, type, id);
        if(!StringUtils.isBlank(fields)){
            return getRequestBuilder.setFetchSource(fields.split(","),null).execute().actionGet().getSource();
        }
        return null;
    }

    /**
     * @param index 数据库名
     * @param type 表名
     * @param builder 查询条件
     * @param size 查询数据条数
     * @param fields 所要显示的字段
     * @param sortField 排序字段
     * @param highLightField 高亮显示字段
     * @return 分词全文检索
     */
    public static List<Map<String,Object>> selectAllData(String index, String type, QueryBuilder builder, Integer size, String fields, String sortField, String highLightField){
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(index);
        // 查询的表不为空
        if(StringUtils.isNotBlank(type)){
            searchRequestBuilder.setTypes(type.split(", "));
        }
        // 需要高亮显示的字段不为空
        if(StringUtils.isNotBlank(highLightField)){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(highLightField);
            searchRequestBuilder.highlighter(highlightBuilder);
        }
        // 执行查询条件
        searchRequestBuilder.setQuery(builder);
        // 需要显示的字段不为空
        if(StringUtils.isNotBlank(fields)){
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }
        // 需要排序的字段不为空
        if(StringUtils.isNotBlank(sortField)){
            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
        }
        // 查询的记录条数不为空
        if(size != null && size > 0){
            searchRequestBuilder.setSize(size);
        }
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        if(searchResponse.status().getStatus() == 200){
            return handResultMaps(searchResponse,highLightField);
        }
        return null;
    }

    public static List<Map<String,Object>> handResultMaps(SearchResponse searchResponse,String highLightField){
        List<Map<String,Object>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit data : hits) {
            data.getSourceAsMap().put("id",data.getId());
            if(StringUtils.isNotBlank(highLightField)){
                Text[] fragments = data.getHighlightFields().get(highLightField).getFragments();
                if(fragments != null){
                    for (Text fragment : fragments) {
                        sb.append(fragment.toString());
                    }
                    data.getSourceAsMap().put(highLightField,sb.toString());
                }
            }
            list.add(data.getSourceAsMap());
        }
        return list;
    }

    private static Map<String,Object> operationMap(Boolean status){
        if(status){
            resultMap.put("code", StatusEnum.OPERATION_SUCCESS.getCode());
            resultMap.put("msg", StatusEnum.OPERATION_SUCCESS.getMsg());
        }else{
            resultMap.put("code", StatusEnum.OPERATION_FAIL.getCode());
            resultMap.put("msg", StatusEnum.OPERATION_FAIL.getMsg());
        }
        return resultMap;
    }
}
