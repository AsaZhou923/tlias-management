package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TliasManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasManagementApplication.class, args);
	}

}
