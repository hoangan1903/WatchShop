package com.seuit.spring.watchshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WatchshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchshopApplication.class, args);
	}

}
