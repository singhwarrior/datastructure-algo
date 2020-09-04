package com.karma.queue.impl;

import com.karma.queue.Queue;
import com.karma.queue.exception.EmptyQueueException;

public class LinkedListBasedQueue<T> implements Queue<T>{
	
	private int N;
	private Node head;
	private Node tail;

	private class Node{
		T item;
		Node next;
	}
	
	@Override
	public void enqueue(T element) {
		Node node = new Node();
		node.item = element;
		if(isEmpty()) {
			head = new Node();
			head.item = null;
			head.next = node;
			
			tail = new Node();
			tail.item = null;
			tail.next = node;
		}
		
		Node last = tail.next;
		last.next = node;
		tail.next = node;
		N++;
	}

	@Override
	public T dequeue() throws EmptyQueueException{
		if(isEmpty())
			throw new EmptyQueueException("Queue is empty");
		
		Node node = head.next;
		T element = node.item;
		N--;
		if(tail.next == node) {
			head = null;
			tail = null;
			node = null;
		}else {
			head.next = node.next;
			node.next = null;
			node = null;
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
	
	public static void main(String[] args) throws EmptyQueueException {
		Queue<Integer> queue = new LinkedListBasedQueue<>();
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
		queue.enqueue(6);
		System.out.println(queue.dequeue());

	}

}
