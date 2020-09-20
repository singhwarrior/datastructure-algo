package com.karma.sort;

public class SelectionSort implements Sortable{
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			int min = i;
			for(int j=i+1; j < arr.length; j++) 
//				if(arr[j] < arr[min]) 
				if(SortHelper.less(arr[j], arr[min])) min = j; // arr[j] < arr[min]
			SortHelper.exchange(arr, i, min);
		}
		return arr;
	}
	
}
