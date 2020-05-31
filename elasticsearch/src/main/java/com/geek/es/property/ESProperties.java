package com.geek.es.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description :
 */
@Component
@ConfigurationProperties(prefix = "spring.elasticsearch")
@Data
public class ESProperties {
    private String ip;
    private Integer port;
    private String clusterName;
    private String nodeName;
    private Integer pool;
}
