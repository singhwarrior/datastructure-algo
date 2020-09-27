package com.karma.sort;

public class HeapSort {
	
	public static <Key extends Comparable<Key>>void sort(Key[] arr) {
		int N = arr.length;
		
//		for(int idx = N; idx > 1; idx--)
//			swim(arr, idx);
		
		for(int idx = N/2; idx >= 1; idx--)
			sink(arr, idx, N);
		
		while(N > 1) {
			SortHelper.exchange(arr, N-1, 0);
			sink(arr, 1, --N);
		}
	}
	
	public static <Key extends Comparable<Key>> void swim(Key[] arr, int idx) {
		while(idx > 1) {
			if(SortHelper.less(arr[idx-1], arr[idx/2-1])) break;
			SortHelper.exchange(arr, idx-1, idx/2-1);
			idx = idx/2;
		}
	}
	
	public static <Key extends Comparable<Key>> void sink(Key[] arr, int idx, int N) {
		while(2*idx <= N) {
			int j = 2*idx;
			if(j < N && SortHelper.less(arr[j-1], arr[j])) j++;
			if(!SortHelper.less(arr[idx-1], arr[j-1])) break;
			SortHelper.exchange(arr, idx-1, j-1);
			idx = j;
		}
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[]{10, 25, 12, -1, 5, 4, 6, 7, 8, 50};
		sort(arr);
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}
	
}
