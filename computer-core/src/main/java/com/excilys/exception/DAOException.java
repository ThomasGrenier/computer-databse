package com.excilys.exception;

@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DAOException(Throwable cause) {
		super(cause);
	}
}
