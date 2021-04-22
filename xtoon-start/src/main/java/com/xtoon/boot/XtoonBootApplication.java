package com.xtoon.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class XtoonBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(XtoonBootApplication.class, args);
	}

}
