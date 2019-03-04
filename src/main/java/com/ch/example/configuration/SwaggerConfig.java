package com.ch.example.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * http://localhost:端口/swagger-ui.html
 * Created by chenhao on 2019/2/28.
 */
@Configuration
public class SwaggerConfig {

    private static final Logger logger = LogManager.getLogger(DruidConfiguration.class);

    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createRestApi() {
        logger.debug("swagger装配开始...............");
        return new Docket(DocumentationType.SWAGGER_12)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ch"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo")
                .description("powered by ch")
                .termsOfServiceUrl("")
                //.contact(contact)
                .version("1.0")
                .build();
    }
}
