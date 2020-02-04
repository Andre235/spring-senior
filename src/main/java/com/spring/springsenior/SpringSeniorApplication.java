package com.spring.springsenior;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * 体验基于spring注解缓存技术
 * 1.开启基于注解的缓存机制
 *      @EnableCaching
 * 2.在方法上添加相应的缓存注解
 *      @Cacheable
 *      @CacheEvict
 *      @CachePut
 */
@EnableRabbit //开启基于注解的RabbitMQ模式
@EnableJpaAuditing
@SpringBootApplication
@EnableCaching //开启基于注解的缓存机制
public class SpringSeniorApplication {

    public static void main(String[] args) {
        /**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SpringSeniorApplication.class, args);
    }

}
