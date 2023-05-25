package com.ntendencia.config;

import com.ntendencia.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public boolean instatiateDatabase(DBService dbService) {
		dbService.instantiateTestDataBase();
		return true;
	}
}
