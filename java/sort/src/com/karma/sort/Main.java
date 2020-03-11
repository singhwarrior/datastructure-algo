package com.karma.sort;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Integer> elements = new ArrayList<>();
		elements.add(10);
		elements.add(12);
		elements.add(25);
		elements.add(3);
		elements.add(1);
		elements.add(2);
		elements.add(8);
//		elements.add(8);
//		elements.add(8);
//		elements.add(8);
//		elements.add(8);
		elements.add(5);
		elements.add(9);
		elements.add(11);
		elements.add(19);
//		elements.add(11);
//		elements.add(11);
		System.out.println(elements);
//		SortUtil.quickSort(elements, 0, elements.size()-1);
		SortUtil.randomizedQuickSort(elements, 0, elements.size()-1);
		System.out.println(elements);
	}
}
