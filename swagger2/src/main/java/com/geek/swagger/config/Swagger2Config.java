//package com.geek.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author : 赵静超
// * @date : 2020/5/9
// * @description :
// */
//@EnableSwagger2
//@Configuration
//// 该注解表示在dev环境和test环境下，该配置文件才有效，即可以访问swagger-ui.html页面
////@Profile({"dev","test"})
//// 该注解表示在特定的环境下才能开启swagger接口文档功能，这里为开发环境开启swagger功能
//@ConditionalOnProperty(name = "swagger.enable",havingValue = "true")
//public class Swagger2Config {
//
//    @Bean
//    public Docket getDocket(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("demo")
//                .apiInfo(getApiInfo())
//                .select()
//                // 设置basePackage参数后会将该包下的所有类的所有方法作为api同步到接口文档中
//                //.apis(RequestHandlerSelectors.basePackage("com.geek.controller"))
//                // 设置withMethodAnnotation参数会将使用ApiOperation注解标注下的方法作为api同步到接口文档中
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.regex("/api/.*"))
//                .build();
//    }
//
//    /**
//     * @return 创建ApiInfo对象
//     */
//    private ApiInfo getApiInfo(){
//        return new ApiInfoBuilder()
//                .title("API接口文档")
//                .description("restful风格接口文档")
//                .termsOfServiceUrl("https://blog.csdn.net/vbirdbest") // 服务条款地址
//                .version("1.0")
//                .contact(new Contact("andre","http://www.geek.com","andre@gmail.com"))
//                .build();
//    }
//}
