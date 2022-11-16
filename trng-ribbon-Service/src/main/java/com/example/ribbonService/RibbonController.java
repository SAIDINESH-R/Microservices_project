package com.example.ribbonService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
	@Value("${server.port}")
	String serverPort;

	
	@GetMapping("/")
	public String home()
	{
		return "Welcome to LOAD BALANCE  DEMO";
	}
	
	
	
	
	@GetMapping("/ser")
	public String getLocationName()
	{
		List<String> names=Arrays.asList("User","Product","Inventory","Cart","Order");
		Random random=new Random();
		int randnum=random.nextInt(names.size());
		return names.get(randnum)+ "-- Message from Port Number "+serverPort;
	}
	
}
