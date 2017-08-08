package com.goku.druiddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class DruidDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}
}
