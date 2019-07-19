/**
 * Configuration for Swagger.
 *
 * created on: 19.07.2019
 * created by: Norman Schmidt
 *
 * Copyright (c) 2019, Cegeka Deutschland GmbH
 */

package de.cegeka.springboot.demoapi.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
  @Value("${api.version:}")
  private String version;
  
  @Bean
  public Docket api() {
     return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("de.cegeka.springboot.demoapi")).paths(PathSelectors.any()).build()
           .apiInfo(apiInfo());
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfo("Demo API", "This is an API for learning Spring Boot", version, null, null, null, null, Collections.emptyList());
 }

}
