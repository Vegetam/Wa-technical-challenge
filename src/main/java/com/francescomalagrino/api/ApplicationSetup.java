package com.francescomalagrino.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApplicationSetup {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationSetup.class);

	public static void main(String[] args) {
		logger.info(":::APPLICATION STARTUP ::: START::");

		SpringApplication.run(ApplicationSetup.class, args);
		
		logger.info(":::APPLICATION STARTUP ::: END ::");
	}

}
