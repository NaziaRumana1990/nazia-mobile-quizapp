package com.nazia.app.mobilequizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.nazia.app.mobilequizapi"},exclude = HibernateJpaAutoConfiguration.class)
public class SpringBootRestApplication {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	     return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {	
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}
