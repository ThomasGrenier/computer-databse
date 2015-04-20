package com.excilys.exception;

@SuppressWarnings("serial")
public class MapperException extends RuntimeException {

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MapperException(Throwable cause) {
		super(cause);
	}
}
