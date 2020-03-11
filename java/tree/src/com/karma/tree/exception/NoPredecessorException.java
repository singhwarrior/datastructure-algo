package com.karma.tree.exception;

public class NoPredecessorException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoPredecessorException() {
		super();
	}
	
	public NoPredecessorException(String message) {
		super(message);
	}

	public NoPredecessorException(Throwable t) {
		super(t);
	}
	
	public NoPredecessorException(String message, Throwable t) {
		super(message,t);
	}

}
