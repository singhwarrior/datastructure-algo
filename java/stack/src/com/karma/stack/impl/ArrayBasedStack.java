package com.karma.stack.impl;

import com.karma.stack.Stack;
import com.karma.stack.exception.StackEmptyException;
import com.karma.stack.exception.StackFullException;

public class ArrayBasedStack<T> implements Stack<T>{
	
	private T[] elements;
	private int N;
	
	public ArrayBasedStack() {
		elements = (T[]) new Object[1];
	}
	
	@Override
	public void push(T element) throws StackFullException {
		if(elements.length == N) 
			resize(2*N);
		elements[N++] = element;
	}

	@Override
	public T pop() throws StackEmptyException {
		if(isEmpty())
			throw new StackEmptyException();
		
		T element = elements[--N];
		elements[N] = null;
		if(elements.length/4 == N) 
			resize(elements.length/2);
		return element;
	}

	@Override
	public Boolean isEmpty() {
		return N==0;
	}

	@Override
	public Boolean isFull() {
		return null;
	}

	@Override
	public T top() throws StackEmptyException {
		if(isEmpty())
			throw new StackEmptyException();
		return elements[N-1];
	}
	
	private void resize(int size) {
		T[] resized_elements = (T[]) new Object[size];
		for(int i = 0; i < N; i++)
			resized_elements[i] = elements[i];
		elements = resized_elements;
	}
	
	public static void main(String[] args) throws StackFullException, StackEmptyException {
		Stack<Integer> myStack = new ArrayBasedStack<>();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
		myStack.pop();
//		System.out.println(myStack.top());
	}
}
