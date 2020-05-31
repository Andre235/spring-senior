package com.geek.mission.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author : Jeffersonnn
 * @date : 2020/2/3
 * @description : 自定义缓存的key属性生成策略
 */
@Configuration //声明这是一个配置类(同时也将该类注入到spring容器中)
public class KeyGeneratorConfig {

    /**
     * @Bean 将该方法注入到容器中
     * @return
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+ Arrays.asList(params).toString()+"]";
            }
        };
    }
}
