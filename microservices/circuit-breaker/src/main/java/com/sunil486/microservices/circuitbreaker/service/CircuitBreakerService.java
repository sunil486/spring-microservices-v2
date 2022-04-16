package com.sunil486.microservices.circuitbreaker.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class CircuitBreakerService {

	@Value("${service2.url:http://localhost:6060/service2}")
	String serviceUrl;

	@CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "hardcodedResponse")
	@Retry(name = "myRetry")
	public String fetchData(String data) {
		System.out
				.println("Making a request to " + serviceUrl + " for data '" + data + "' at : " + LocalDateTime.now());

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(serviceUrl, String.class);
	}

	@RateLimiter(name = "myRateLimiter")
	public String rateLimiter(String data) {
		System.out.println("Rate Limiter for data '" + data + "' at : " + LocalDateTime.now());

		return "rate limiter hardcoded value";
	}

	@Bulkhead(name = "myBulkhead")
	public String bulkHead(String data) {
		System.out.println("Bulk Head for data '" + data + "' at : " + LocalDateTime.now());

		return "bulk head hardcoded value";
	}

	public String hardcodedResponse(String data, Exception ex) {
		return "fallback value " + data + " with exception" + ex.getMessage();
	}
}
