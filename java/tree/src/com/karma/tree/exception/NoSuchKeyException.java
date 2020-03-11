package com.karma.tree.exception;

public class NoSuchKeyException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuchKeyException() {
		super();
	}
	
	public NoSuchKeyException(String message) {
		super(message);
	}

	public NoSuchKeyException(Throwable t) {
		super(t);
	}
	
	public NoSuchKeyException(String message, Throwable t) {
		super(message,t);
	}
	
}
