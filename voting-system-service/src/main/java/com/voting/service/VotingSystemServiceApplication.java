package com.voting.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.voting.db")
@EntityScan("com.voting.model")
@ComponentScan("com.voting")
public class VotingSystemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSystemServiceApplication.class, args);
	}
}
