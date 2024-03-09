package com.istudyenglish.mobilebackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.Instant;

@SpringBootApplication
@Log4j2
public class MobilebackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(MobilebackendApplication.class, args);
		log.info("App was been started");
	}

}
