package com.ray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeansApplication {

	//这个项目用来验证Spring构造Bean为单例模式


	public static void main(String[] args) {
		SpringApplication.run(BeansApplication.class, args);
	}
}
