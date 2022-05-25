package com.mirandasidney.pdv.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagerPdvApplication {
	public static void main(String[] args) {
//		System.out.println("SENHA: "+ new BCryptPasswordEncoder().encode("admin"));
		SpringApplication.run(ManagerPdvApplication.class, args);
	}
}
