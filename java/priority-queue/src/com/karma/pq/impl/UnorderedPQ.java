package com.karma.pq.impl;

import com.karma.pq.Queue;
import com.karma.pq.exception.QueueEmptyException;
import com.karma.pq.exception.QueueFullException;
import com.karma.pq.util.PQHelper;

/**
 * Implementation of fixed sized Priority Queue. 
 * 
 * Implements two APIs which is enqueue and dequeue. enqueue puts the key in 
 * the queue. While dequeue returns back the key with maximum value and 
 * removes it from the queue. 
 * 
 * As name suggests the implementation is unordered i.e. key will be enqueued
 * without any order hence it takes O(1) time. While dequeue is takes O(N)
 * time because while dequeue it identifies the maximum key from the 
 * unordered array and shifts all elements to left by 1.
 * 
 * @author singh_warrior
 */
public class UnorderedPQ<Key extends Comparable<Key>> implements Queue<Key>{

	private Key[] data;
	private int N;
	private final int SIZE;
	
	public UnorderedPQ(int capacity) {
		this.data = (Key[])new Comparable[capacity];
		N = 0;
		SIZE = capacity;
	}
	
	@Override
	public void enqueue(Key key) throws QueueFullException{
		if(N == SIZE) throw new QueueFullException("Queue is full!!");
		data[N++] = key;
	}

	@Override
	public Key dequeue() throws QueueEmptyException{
		if(isEmpty()) throw new QueueEmptyException("Queue is empty!!");
		int max = 0;
		for(int i = 1; i < N; i++) 
			if(PQHelper.less(data[max], data[i])) 
				max = i;
			
		Key element = data[max];
		
		for(int i = max; i < N-1; i++)
			data[i] = data[i+1];
		
		data[--N] = null;                
			
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

}
