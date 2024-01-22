package com.bestoctopus.dearme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DearmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearmeApplication.class, args);
	}

}
