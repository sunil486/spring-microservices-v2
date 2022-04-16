package com.sunil486.microservices.circuitbreaker.exception;

public class IgnoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IgnoreException(String str) {
		// calling the constructor of parent Exception
		super(str);
	}
}
