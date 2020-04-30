package com.lokesh.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieCatalogServiceApplication {


	@Bean
	@LoadBalanced 
	public WebClient.Builder getWeb(){
		WebClient.Builder webclient =  WebClient.builder();
		return webclient;
	}

	//Tell restTemplate to do load balancing,client side service discovery,
	//Also tell RestTemplate, don't fetch service directely, instead find by service name 
	// registered on eureka server
	@Bean
	@LoadBalanced 
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
