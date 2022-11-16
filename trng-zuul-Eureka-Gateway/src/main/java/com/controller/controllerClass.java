package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;

@RestController

public class controllerClass {
	// Hystrix
	@Autowired
	GreetingService greetingservice;

	@RequestMapping("/get-greeting/{username}")
	public String greetString(@PathVariable("username") String username) {

		return greetingservice.getGreeting(username);
	}

	// Load Balancing
	@Autowired
	EurekaClient eurekaClient;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping("/service")
	public String demoRibbon(@RequestParam(value = "activity", defaultValue = "Available Service: ") String activity)
			throws InterruptedException {

		// TimeUnit.SECONDS.sleep(5);

		String subject = this.restTemplate.getForObject("http://ribbon-service1-app/ser", String.class);
		return String.format("%s %s", activity, subject);
	}

	@GetMapping("/")
	public String getWelcome() {
		RestTemplate restTemplate = restTemplateBuilder.build();

		return "Use /service to see load balance with round robin algorithm";
	}
}
