package com.karma.sort;

public class QuickSort implements Sortable {

	@Override
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
		return arr;
	}

	private <T extends Comparable<T>> void quickSort(T[] arr, int start, int end) {

		if (start >= end)
			return;

		int pivotIdx = pivotIndex(arr, start, end);
		quickSort(arr, start, pivotIdx - 1);
		quickSort(arr, pivotIdx + 1, end);

	}
	
	private <T extends Comparable<T>> int pivotIndex(T[] arr, int start, int end) {
		
		T pivot = arr[start];
		int i = start + 1;
		int j = end;

		while (i < j) {
			while (i < end && (SortHelper.less(arr[i], pivot)))
				i++;
			while (j > start + 1 && SortHelper.less(pivot, arr[j]))
				j--;
			if (i < j)
				SortHelper.exchange(arr, i, j);
		}

		SortHelper.exchange(arr, start, j);
		return j;
	}
	
	

}
