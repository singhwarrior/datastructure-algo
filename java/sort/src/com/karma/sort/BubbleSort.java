package com.karma.sort;

public class BubbleSort implements Sortable{

	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		
		for(int i = arr.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
//				if(arr[j] > arr[j+1])
				if(SortHelper.less(arr[j+1], arr[j])) // arr[j+1] < arr[j]
					SortHelper.exchange(arr, j, j+1);
			}
		}
		
		return arr;
	}
	
}
