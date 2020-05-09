package com.geek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : 赵静超
 * @date : 2020/5/9
 * @description : 配置spring security拦截swagger2-ui.html页面，同时也会拦截该页面下的所有api，这里将忽略掉/api/**下的所有子路径
 */
@Configuration
@EnableWebSecurity
public class Swagger2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/api/**");
    }
}
