package com.geek.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : 赵静超
 * @date : 2020/5/9
 * @description :
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo")
                .apiInfo(getApiInfo())
                .select()
                // 设置basePackage参数后会将该包下的所有类的所有方法作为api同步到接口文档中
                //.apis(RequestHandlerSelectors.basePackage("com.geek.controller"))
                // 设置withMethodAnnotation参数会将使用ApiOperation注解标注下的方法作为api同步到接口文档中
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.regex("/api/.*"))
                .build();
    }

    /**
     * @return 创建ApiInfo对象
     */
    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("restful风格接口文档")
                .termsOfServiceUrl("https://blog.csdn.net/vbirdbest") // 服务条款地址
                .version("1.0")
                .contact(new Contact("andre","http://www.geek.com","andre@gmail.com"))
                .build();
    }
}
