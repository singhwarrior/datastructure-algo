package com.karma.pq.impl;

import com.karma.pq.Queue;
import com.karma.pq.exception.HeapEmptyException;
import com.karma.pq.exception.HeapFullException;
import com.karma.pq.exception.QueueEmptyException;
import com.karma.pq.exception.QueueFullException;

public class HeapPQ<Key extends Comparable<Key>> implements Queue<Key>{

	private MaxHeap<Key> heap;
	
	public HeapPQ(int capacity) {
		heap = new MaxHeap<>(capacity);
	}
	
	@Override
	public void enqueue(Key key) throws QueueFullException {
		try {
			this.heap.insertKey(key);
		} catch (HeapFullException e) {
			throw new QueueFullException("Queue is full!!");
		}
	}

	@Override
	public Key dequeue() throws QueueEmptyException {
		try {
			Key key = this.heap.delMax();
			return key;
		} catch (HeapEmptyException e) {
			throw new QueueEmptyException("Queue is empty!!");
		}
	}

	@Override
	public int size() {
		return this.heap.size();
	}

	@Override
	public boolean isEmpty() {
		return this.heap.isEmpty();
	}

}
