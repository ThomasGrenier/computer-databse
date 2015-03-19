package com.excily.exception;

@SuppressWarnings("serial")
public class MapperException extends RuntimeException {

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MapperException(Throwable cause) {
		super(cause);
	}
}
