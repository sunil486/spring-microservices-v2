package com.sunil486.microservices.circuitbreaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sunil486.microservices.circuitbreaker.service.CircuitBreakerService;

@RestController
public class CircuitBreakerController {

	@Autowired
	private CircuitBreakerService circuitBreakerService;

	@GetMapping("/values/{data}")
	public String getValues(@PathVariable String data) {
		return circuitBreakerService.fetchData(data);
	}

	@GetMapping("/values/ratelimiter/{data}")
	public String getRateLimiterValues(@PathVariable String data) {
		return circuitBreakerService.rateLimiter(data);
	}

	@GetMapping("/values/bulkhead/{data}")
	public String getBulkHeadValues(@PathVariable String data) {
		return circuitBreakerService.bulkHead(data);
	}
}
