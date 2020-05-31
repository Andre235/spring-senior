package com.geek.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author : 赵静超
 * @date : 2020/5/31
 * @description :
 */
@EnableScheduling //开启基于注解的定时任务
@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
@EnableCaching //开启基于注解的缓存机制
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
