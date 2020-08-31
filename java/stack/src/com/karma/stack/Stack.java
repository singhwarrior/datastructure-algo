package com.karma.stack;

import com.karma.stack.exception.StackEmptyException;
import com.karma.stack.exception.StackFullException;

public interface Stack<T> {
	public void push(T element) throws StackFullException;
	public T pop() throws StackEmptyException;
	public Boolean isEmpty();
	public Boolean isFull();
	public T top() throws StackEmptyException;
}
