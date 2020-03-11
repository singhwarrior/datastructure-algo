package com.karma.tree.exception;

public class DuplicateKeyException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateKeyException() {
		super();
	}
	
	public DuplicateKeyException(String message) {
		super(message);
	}

	public DuplicateKeyException(Throwable t) {
		super(t);
	}
	
	public DuplicateKeyException(String message, Throwable t) {
		super(message,t);
	}
	
}
