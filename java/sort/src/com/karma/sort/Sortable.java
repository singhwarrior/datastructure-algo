package com.karma.sort;

public interface Sortable {
	<T extends Comparable<T>> T[] sort(T[] arr);
//	<T extends Comparable<T>> T[] sort(T[] arr, Comparator<T> comparator);
}
