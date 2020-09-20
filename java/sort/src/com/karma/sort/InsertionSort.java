package com.karma.sort;

public class InsertionSort implements Sortable{

	@Override
	public <T extends Comparable<T>>T[] sort(T[] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = i; j > 0; j--) 
//				if(arr[j] < arr[j-1])
				if(SortHelper.less(arr[j], arr[j-1]))   // arr[j] < arr[j-1]	
					SortHelper.exchange(arr, j, j-1);
				else
					break;
		}
		return arr;
	}

}
