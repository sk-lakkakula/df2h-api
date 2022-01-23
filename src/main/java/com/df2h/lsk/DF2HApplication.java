package com.df2h.lsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableSwagger2
public class DF2HApplication extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(DF2HApplication.class);
	public static void main(String[] args) {
		logger.info("Testing the Log4j");
		SpringApplication.run(DF2HApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		logger.info("Testing the Log4j");
		return builder.sources(DF2HApplication.class);
	}
}
