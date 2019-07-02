//package com.example.swagger.config;
//
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @description:
// * @author: ningcs
// * @create: 2019-07-01 13:37
// **/
//
//public class SwaggerConfiguration {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.swagger.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Student info query api")
//                .description("Some API to operator student,teacher information")
//                .termsOfServiceUrl("http://localhost:8086/")
//                .version("1.0")
//                .build();
//    }
//}