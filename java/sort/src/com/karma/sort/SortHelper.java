package com.karma.sort;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class SortHelper {
	
	public static <T extends Comparable<T>> boolean less(T a, T b) {
		return a.compareTo(b) < 0;
	}
	
	public static <T extends Comparable<T>> void exchange(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static <T extends Comparable<T>> void print(BufferedWriter bw, String sep, SortableFactory.SORTABLE sortable, T[] arr) {
		try {
			bw.write("=============="+sortable.getName()+"==============");
			bw.newLine();
			Client client = new Client(SortableFactory.getInstance().getSortable(sortable));
			arr = client.sort(arr);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < arr.length; i++) {
				sb.append(arr[i].toString()+sep);
			}
			bw.write(sb.toString());
			bw.newLine();
		} catch (IOException e) {
			System.out.println("Error");
		}
		
	}
}
