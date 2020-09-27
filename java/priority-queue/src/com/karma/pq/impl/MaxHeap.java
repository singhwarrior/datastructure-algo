package com.karma.pq.impl;

import com.karma.pq.exception.HeapEmptyException;
import com.karma.pq.exception.HeapFullException;

public class MaxHeap<Key extends Comparable<Key>> {
	
	private Key[] data;	
	private int N;
	private final int SIZE;
	
	public MaxHeap(int capacity) {
		this.data = (Key[])new Comparable[capacity+1];
		this.SIZE = capacity;
		this.N = 0;
	}
	
	public void insertKey(Key key) throws HeapFullException{
		if(N == SIZE) throw new HeapFullException("Heap is full!!");
		data[++N] = key;
		swim(N);
	}
	
	public Key delMax() throws HeapEmptyException{
		if(isEmpty()) throw new HeapEmptyException("Heap is empty!!");
		
		exchange(1, N);
		Key key = data[N];
		data[N--] = null;
		sink(1);
		return key;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return this.N;
	}
	
	private void swim(int idx) {
		while(idx > 1) {
			if(!less(idx/2, idx)) break;
			exchange(idx/2, idx);
			idx = idx/2;
		}
	}
	

	
	private void sink(int idx) {
		while(2*idx <= N) {
			int j = 2*idx;
			if(j < N && less(j, j+1)) j++;
			if(!less(idx, j)) break;
			exchange(idx, j);
			idx = j;
		}
	}
	
	private boolean less(int i, int j) {
		return this.data[i].compareTo(this.data[j]) < 0;
	}
	
	private void exchange(int i, int j) {
		Key key = this.data[i];
		this.data[i] = this.data[j];
		this.data[j] = key;
	}
	
	public static void main(String[] args) throws HeapFullException, HeapEmptyException {
		MaxHeap<Integer> heap = new MaxHeap<>(10);
		
		heap.insertKey(10);
		heap.insertKey(25);
		heap.insertKey(12);
		heap.insertKey(-1);
		heap.insertKey(5);
		heap.insertKey(4);
		heap.insertKey(6);
		heap.insertKey(7);
		heap.insertKey(8);
		heap.insertKey(50);
		
		while(!heap.isEmpty()) {
			System.out.print(heap.delMax()+" ");
		}

	}
	
}
