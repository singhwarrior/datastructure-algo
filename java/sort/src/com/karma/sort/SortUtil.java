package com.karma.sort;

import java.util.List;
import java.util.Random;

public class SortUtil {
	public static <T extends Comparable<T>> void quickSort(List<T> in, int from, int to) {
		if (from >= 0 && to >= 0 && from < to) {
			int pivot = partition(in, from, to);
			quickSort(in, from, pivot - 1);
			quickSort(in, pivot + 1, to);
		}
	}

	public static <T extends Comparable<T>> void randomizedQuickSort(List<T> in, int from, int to) {
		if (from >= 0 && to >= 0 && from < to) {
			int pivot = randomizedPartition(in, from, to);
			quickSort(in, from, pivot - 1);
			quickSort(in, pivot + 1, to);
		}
	}
	
	private static <T extends Comparable<T>> int partition(List<T> in, int from, int to) {
		int pivotPosition = (from + to)/2;
		T pivotElement = in.get(pivotPosition);
		while(from < to) {
			while(in.get(from).compareTo(pivotElement) == -1)
				from++;
			while(in.get(to).compareTo(pivotElement) == 1)
				to--;
			swap(in, from, to);
		}
		return from;
	}
	
	private static <T extends Comparable<T>> int randomizedPartition(List<T> in, int from, int to) {
		Random random = new Random();
		int pivotPosition = from + random.nextInt(to-from);
		T pivotElement = in.get(pivotPosition);
		while(from < to) {
			while(in.get(from).compareTo(pivotElement) == -1)
				from++;
			while(in.get(to).compareTo(pivotElement) == 1)
				to--;
			swap(in, from, to);
		}
		return from;
	}

	private static <T> void swap(List<T> in, int i, int j) {
		T temp = in.get(i);
		in.set(i, in.get(j));
		in.set(j, temp);
	}
}
