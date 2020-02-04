package com.spring.springsenior.ElasticSearch.entity;

import io.searchbox.annotations.JestId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/4
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "atguigu",type = "article") //指定索引名称(数据库名)和类别名称(表名)
public class Article {

    @JestId
    private Integer id;
    private String author;
    private String title;
    private String content;
}
