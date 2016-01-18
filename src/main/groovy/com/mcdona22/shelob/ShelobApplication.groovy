package com.mcdona22.shelob

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShelobApplication {
	final static Logger LOG = LoggerFactory.getLogger(ShelobApplication)

	static void main(String[] args) {
        LOG.info "about to launch Shelob"

		SpringApplication.run ShelobApplication, args
	}
}
