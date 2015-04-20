package com.excilys.exception;

@SuppressWarnings("serial")
public class ConnectionException extends RuntimeException {
	
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ConnectionException(Throwable cause) {
		super(cause);
	}
}
