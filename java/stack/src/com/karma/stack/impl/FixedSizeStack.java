package com.karma.stack.impl;

import java.util.ArrayList;
import java.util.List;

import com.karma.stack.Stack;
import com.karma.stack.exception.StackEmptyException;
import com.karma.stack.exception.StackFullException;

public class FixedSizeStack<T> implements Stack<T> {

	private static final int DEFAULT = 10;
	private int size;
	private List<T> elements;
	private int top = -1;
	public FixedSizeStack() {
		size = DEFAULT;
		elements = new ArrayList<>();
	}
	
	public FixedSizeStack(int size) {
		this.size = size; 
		elements = new ArrayList<>();
	}
	
	@Override
	public void push(T element) throws StackFullException {
		if(!isFull()) {
			elements.add(element);
			top++;
		}else {
			throw new StackFullException();
		}
	}

	@Override
	public T pop() throws StackEmptyException {
		T element = null;
		if(!isEmpty()) {
			element = elements.get(top);
			elements.remove(top);
			top--;
		}else {
			throw new StackEmptyException();
		}
		return element;
	}

	@Override
	public Boolean isEmpty() {
		return top == -1;
	}

	@Override
	public Boolean isFull() {
		return top == size-1;
	}

	@Override
	public T top() throws StackEmptyException{
		if(top == -1)
			throw new StackEmptyException();
		return elements.get(top);
	}
	
	

}
