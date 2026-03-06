package com.Challenge.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		// TEMPORAL - solo para generar el hash
		System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("123456"));

		SpringApplication.run(DemoApplication.class, args);
	}}