package com.karma.tree.exception;

public class NoSuccessorException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoSuccessorException() {
		super();
	}
	
	public NoSuccessorException(String message) {
		super(message);
	}

	public NoSuccessorException(Throwable t) {
		super(t);
	}
	
	public NoSuccessorException(String message, Throwable t) {
		super(message,t);
	}

}
