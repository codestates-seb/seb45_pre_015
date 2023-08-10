package com.preproject.seb_pre_15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SebPre15Application {

	public static void main(String[] args) {
		SpringApplication.run(SebPre15Application.class, args);
	}

}
