package com.mcdona22.shelob

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@SpringBootApplication
class Application {
	final static Logger LOG = LoggerFactory.getLogger(Application)

	static void main(String[] args) {
		LOG.info "starting the application"
		SpringApplication application = new SpringApplication(Application.class)
		application.showBanner = false
		application.run(args)
		LOG.info "application startup complete"
	}

	@Bean
	Validator validator(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.validator
	}
}
