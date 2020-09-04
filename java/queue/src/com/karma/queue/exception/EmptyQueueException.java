package com.karma.queue.exception;

public class EmptyQueueException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmptyQueueException(String msg) {
		super(msg);
	}

}
