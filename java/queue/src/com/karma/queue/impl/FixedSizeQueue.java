package com.karma.queue.impl;

import java.util.List;

import com.karma.queue.Queue;

public class FixedSizeQueue<T> implements Queue<T>{

	private static final int DEFAULT = 10;
	private int size;
	private List<T> elements;
	private int top = -1;
	
	@Override
	public void enqueue(T element) {
		
	}

	@Override
	public T dequeue() {
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
