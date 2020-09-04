package com.karma.queue;

import com.karma.queue.exception.EmptyQueueException;

public interface Queue<T> {
	public void enqueue(T element);
	public T dequeue() throws EmptyQueueException;
	public int size();
	public boolean isEmpty();
}
