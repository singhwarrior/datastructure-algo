package com.karma.pq.exception;

public class QueueEmptyException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QueueEmptyException(String message) {
		super(message);
	}

}
