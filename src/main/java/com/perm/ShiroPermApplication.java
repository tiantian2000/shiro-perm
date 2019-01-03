package com.perm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ShiroPermApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroPermApplication.class, args);
	}

}

