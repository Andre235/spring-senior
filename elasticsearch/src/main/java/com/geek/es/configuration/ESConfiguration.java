package com.geek.es.configuration;

import com.geek.es.property.ESProperties;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import sun.rmi.transport.Transport;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description : 配置ES集群的基本信息
 */
@Configuration
@Slf4j
public class ESConfiguration {

    @Autowired
    private ESProperties properties;

    @PostConstruct
    void print(){
        log.warn(properties.toString());
    }

    /**
     * @return 通过TransportClient对象可以对ES集群中的数据进行 增删改查
     */
    @Bean(name = "transportClient")
    public TransportClient getTransportClient(){
        // 1.创建TransportClient对象，不用初始化
        TransportClient transportClient = null;
        // 2.配置ES集群信息
        Settings build = Settings.builder()
                .put("cluster.name", properties.getClusterName())
                .put("node.name", properties.getNodeName())
                .put("client.transport.sniff", true) // 当ES集群中有新的节点加入时，项目会自动发现并加入这个节点，不需要手动添加该节点信息
                .put("thread_pool.search.size", properties.getPool()).build(); // ES线程池
        // 3.对TransportClient对象初始化
        transportClient = new PreBuiltTransportClient(build);
        try{
            // 4.配置ES的连接信息
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(properties.getIp()),properties.getPort());
            // 5.把ES的连接信息放入TransportClient对象中
            transportClient.addTransportAddress(transportAddress);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        // 6.返回TransportClient对象
        return transportClient;
    }
}
