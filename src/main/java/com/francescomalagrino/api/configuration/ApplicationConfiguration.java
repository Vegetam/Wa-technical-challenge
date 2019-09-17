package com.francescomalagrino.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

	
	/**
	 * Method look for base package to load REST API into swagger
	 */
	@Bean
	public Docket api() {
		logger.info("start of the api configuration::");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.francescomalagrino.api.controller"))
				.paths(PathSelectors.any()).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("start of the api configuration::	addResourceHandlers()");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
