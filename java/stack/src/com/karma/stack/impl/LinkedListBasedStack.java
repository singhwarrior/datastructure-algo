package com.karma.stack.impl;

import com.karma.stack.Stack;
import com.karma.stack.exception.StackEmptyException;
import com.karma.stack.exception.StackFullException;

public class LinkedListBasedStack<T> implements Stack<T>{

	private Node head;
	
	private class Node{
		T element;
		Node next;
	}
	
	@Override
	public void push(T element) throws StackFullException {
		Node node = new Node();
		node.element = element;
		if(isEmpty()) {
			head = new Node();
			head.element = null;
			head.next = node;
		}else {
			node.next = head.next;
			head.next = node;
		}
	}

	@Override
	public T pop() throws StackEmptyException {
		if(isEmpty())
			throw new StackEmptyException();
		
		Node first = head.next;
		
		T element = first.element;
		head.next = first.next;
		
		if(head.next == null) 
			head = null;
		
		first.next = null;
		
		return element;
	}

	@Override
	public Boolean isEmpty() {
		return head == null;
	}

	@Override
	public Boolean isFull() {
		return null;
	}

	@Override
	public T top() throws StackEmptyException {
		if(head == null)
			throw new StackEmptyException();
		
		return head.next.element;
	}
	
	public static void main(String[] args) throws StackFullException, StackEmptyException {
		Stack<Integer> myStack = new LinkedListBasedStack<>();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
		myStack.pop();
//		System.out.println(myStack.top()); // Enable to test exception
	}
	
}
