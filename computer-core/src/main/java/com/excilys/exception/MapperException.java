package com.excilys.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class MapperException.
 */
@SuppressWarnings("serial")
public class MapperException extends RuntimeException {

	/**
	 * Instantiates a new mapper exception.
	 *
	 * @param message the message of the exception
	 * @param cause the cause of the exception
	 */
	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new mapper exception.
	 *
	 * @param cause the cause of the exception
	 */
	public MapperException(Throwable cause) {
		super(cause);
	}
}
