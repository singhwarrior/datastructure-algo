package com.karma.pq.util;

public final class PQHelper {
	public static final <Key extends Comparable<Key>> boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}
	
	public static final <Key extends Comparable<Key>> void exchange(Key[] arr, int i, int j) {
		Key temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp; 
	}
}
