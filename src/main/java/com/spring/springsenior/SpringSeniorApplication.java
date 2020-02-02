package com.spring.springsenior;

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
@EnableJpaAuditing
@SpringBootApplication
@EnableCaching //开启基于注解的缓存机制
public class SpringSeniorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSeniorApplication.class, args);
    }

}
