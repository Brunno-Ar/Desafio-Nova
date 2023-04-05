package com.ntendencia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DesafioNovaApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(DesafioNovaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
