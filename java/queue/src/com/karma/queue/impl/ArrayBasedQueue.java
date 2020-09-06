package com.karma.queue.impl;

import com.karma.queue.Queue;
import com.karma.queue.exception.EmptyQueueException;

public class ArrayBasedQueue<T> implements Queue<T>{
	
	private int N;
	private T[] elements;
	private int head;
	private int tail;
	
	@SuppressWarnings("unchecked")
	public ArrayBasedQueue() {
		elements = (T[]) new Object[1];
		head = -1;
		tail = 0;
	}
	
	@Override
	public void enqueue(T element) {
		if(isEmpty()) 
			head++;
		
		if(N == elements.length) 
			resize(2*N);
		
		N++;
		if(tail == elements.length) 
			tail = 0;
		
		elements[tail++] = element;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() throws EmptyQueueException {
		if(isEmpty())
			throw new EmptyQueueException("Queue is Empty");
		
		if(N == elements.length/4) 
			resize(elements.length/2); 
		
		N--;

		T element = elements[head];
		elements[head++] = null;
		
		if(head == elements.length)
			head = 0;
		
		if(isEmpty()) {
			elements = (T[]) new Object[1];
			head = -1;
			tail = 0;
		}
		
		return element;
	}
	
	@Override
	public int size() {
		return N;
	}
	
	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	@SuppressWarnings("unchecked")
	private void resize(int size) {
		T[] resized_elements = (T[]) new Object[size];
	
		int check = (head < tail) ? tail : elements.length;
		int idx = 0;
		
		while(idx + head < check) {
			resized_elements[idx] = elements[idx+head];
			idx++;
		}
		
		int i = 0;
		while(tail < head && i < tail) {
			resized_elements[idx+i] = elements[i];
			i++;
		}
		
		elements = resized_elements;
		head = 0;
		tail = idx+i;
	}
	
	public static void main(String[] args) throws EmptyQueueException {
		Queue<Integer> queue = new ArrayBasedQueue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		System.out.println("SIZE="+queue.size());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println("SIZE="+queue.size());
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue(9);
		queue.enqueue(10);
		queue.enqueue(11);
		queue.enqueue(12);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}
	
	
}
