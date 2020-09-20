package com.karma.sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Client {
	
	private Sortable sortable;
	
	public Client(Sortable sortable) {
		this.sortable = sortable;
	}
	
	public <T extends Comparable<T>> T[] sort(T[] arr) {
		return sortable.sort(arr);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/singg/my_repos/datastructure-algo/java/sort/sort-out.txt"));
		
		Integer[] array = new Integer[]{5, 12, 21, 36, -1, -5, 10, 12, 13, 15, 89, 91, -21, 100};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.SELECTION_SORT, array);

		array = new Integer[]{5, 12, 21, 36, -1, -5, 10, 12, 13, 15, 89, 91, -21, 100};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.INSERTION_SORT, array);

		array = new Integer[]{5, 12, 21, 36, -1, -5, 10, 12, 13, 15, 89, 91, -21, 100};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.BUBBLE_SORT, array);
		
		array = new Integer[]{5, 12, 21, 36, -1, -5, 10, 12, 13, 15, 89, 91, -21, 100};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.QUICK_SORT, array);
		
		array = new Integer[]{5, 12, 21, 36, -1, -5, 10, 12, 13, 15, 89, 91, -21, 100};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.MERGE_SORT, array);
		
		String[] array1 = new String[]{"B", "A", "C", "D", "E"};
		SortHelper.print(bw, " ", SortableFactory.SORTABLE.QUICK_SORT, array1);
		
		bw.close();
		
	}

}
