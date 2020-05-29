package com.cagong.caferanking;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CaferankingAdminApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "classpath:dev.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(CaferankingAdminApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
