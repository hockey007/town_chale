package com.projects.town_chale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.projects.town_chale.model")
public class TownChaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TownChaleApplication.class, args);
	}

}
