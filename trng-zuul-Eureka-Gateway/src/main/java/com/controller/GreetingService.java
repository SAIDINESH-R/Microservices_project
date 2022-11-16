package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {
	@Autowired
	EurekaClient eurekaClient;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String getGreeting(String username) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("shoppingservice", false);
		String baseUrl = instanceInfo.getHomePageUrl();
		baseUrl = baseUrl + "/greettt/" + username;
		System.out.println(baseUrl);
		return restTemplate.getForObject(baseUrl, String.class);
	}

	public String defaultGreeting(String username) {
		return "Problem Occured! Sorry for the inconvenience caused :(";

	}
}
