package com.karma.pq;

import com.karma.pq.exception.QueueEmptyException;
import com.karma.pq.exception.QueueFullException;

public interface Queue<Key extends Comparable<Key>> {
	void enqueue(Key key) throws QueueFullException;
	Key dequeue() throws QueueEmptyException;
	int size();
	boolean isEmpty();
}
