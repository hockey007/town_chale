package com.projects.town_chale;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TownChaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TownChaleApplication.class, args);
	}

}
