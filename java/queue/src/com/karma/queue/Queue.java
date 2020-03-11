package com.karma.queue;

public interface Queue<T> {
	public void enqueue(T element);
	public T dequeue();
}
