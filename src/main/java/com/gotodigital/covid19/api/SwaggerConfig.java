package com.gotodigital.covid19.api;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/v1/*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);
	   @Bean
	   public Docket apiDocket() {
		   	log.debug("Starting Swagger");
	    		   return new Docket(DocumentationType.SWAGGER_2)   
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.gotodigital.covid19.api.controller"))
	                .paths(PathSelectors.regex("/api/v1/.*"))
	                .build()
	                .apiInfo(apiInfo());
	   }
	   

	
	    
	   private ApiInfo apiInfo() {
	       return new ApiInfo(
	         "COVID-19 API", 
	         "-      ", 
	         "API TOS", 
	         "Terms of service", 
	         new Contact("GoToDigital", "https://gotodigital.es/", " "), 
	         "License of API", "API license URL", Collections.emptyList());
	       
	   }
          
}
