package com.karma.pq.impl;

import com.karma.pq.Queue;
import com.karma.pq.exception.QueueEmptyException;
import com.karma.pq.exception.QueueFullException;
import com.karma.pq.util.PQHelper;

public class OrderedPQ<Key extends Comparable<Key>> implements Queue<Key>{

	private Key data[];
	private int N;
	private final int SIZE;
	
	public OrderedPQ(int capacity) {
		this.SIZE = capacity;
		this.data = (Key[]) new Comparable[capacity];
		this.N = 0;
	}
	
	@Override
	public void enqueue(Key key) throws QueueFullException {
		if(this.N == this.SIZE) throw new QueueFullException("Queue is full!!");
		int idx = 0;
		for(; idx < N; idx++) 
			if(PQHelper.less(key, data[idx])) break;
		
		N++;
		for(int i = N-1; i > idx; i--)
			data[i] = data[i-1];
		
		data[idx] = key;
	}

	@Override
	public Key dequeue() throws QueueEmptyException {
		if(isEmpty()) throw new QueueEmptyException("Queue is empty");
		Key key = data[--N];
		data[N] = null;
		return key;
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
