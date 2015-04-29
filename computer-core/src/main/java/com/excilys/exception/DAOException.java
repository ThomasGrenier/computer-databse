package com.excilys.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOException.
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message of the exception
	 * @param cause the cause of the exception
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param cause the cause of the exception
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
