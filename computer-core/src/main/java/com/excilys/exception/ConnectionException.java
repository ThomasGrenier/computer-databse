package com.excilys.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionException.
 */
@SuppressWarnings("serial")
public class ConnectionException extends RuntimeException {
	
	/**
	 * Instantiates a new connection exception.
	 *
	 * @param message the message of the exception
	 * @param cause the cause of the exception
	 */
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new connection exception.
	 *
	 * @param cause the cause of the exception
	 */
	public ConnectionException(Throwable cause) {
		super(cause);
	}
}
