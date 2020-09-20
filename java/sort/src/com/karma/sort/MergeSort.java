package com.karma.sort;


public class MergeSort implements Sortable{
	
	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) { 
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[arr.length];
		helper(arr, aux, 0, arr.length-1);
		return arr;
	}
	
	
	private <T extends Comparable<T>> void helper(T[] arr, T[] aux, int start, int end) {
		
		if(end <= start) 
			return ;
		
		int mid = (start + end)/2; 
		helper(arr, aux, start, mid);
		helper(arr, aux, mid+1, end);
		merge(arr, aux, start, mid , end);
	}
	
	private <T extends Comparable<T>> void merge(T[] arr, T[] aux, int start, int mid, int end) {
		
		for(int i = start; i <= end; i++)
			aux[i] = arr[i];
		
		int i = start;
		int j = mid+1;
		int k = start;

		while(i <= mid && j <= end) {
			if(SortHelper.less(aux[i], aux[j])) 
				arr[k++] = aux[i++];
			else if(SortHelper.less(aux[j], aux[i])) 
				arr[k++] = aux[j++];
			else {
				arr[k++] = aux[i++];
				arr[k++] = aux[j++];
			}
		}
		
		while(i <= mid) 
			arr[k++] = aux[i++];
		
		while(j <= end)
			arr[k++] = aux[j++];
		
	}

}
