package com.demo.aviationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AviationapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AviationapiApplication.class, args);
	}

}
