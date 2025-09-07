package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.controller.EnquiryController;

@SpringBootApplication
public class Application {

    private final EnquiryController enquiryController;

    Application(EnquiryController enquiryController) {
        this.enquiryController = enquiryController;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		
	}
}
