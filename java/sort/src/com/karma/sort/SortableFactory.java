package com.karma.sort;

import java.util.Map;
import java.util.HashMap;

public class SortableFactory {
	
	private Map<SORTABLE, Sortable> sortables = new HashMap<>();
	private static final SortableFactory _instance = new SortableFactory();
	
	private SortableFactory() {
		sortables.put(SORTABLE.SELECTION_SORT, new SelectionSort());
		sortables.put(SORTABLE.INSERTION_SORT, new InsertionSort());
		sortables.put(SORTABLE.BUBBLE_SORT, new BubbleSort());
		sortables.put(SORTABLE.QUICK_SORT, new QuickSort());
		sortables.put(SORTABLE.MERGE_SORT, new MergeSort());
	}
	
	public static final SortableFactory getInstance() {
		return _instance;
	}
	
	public Sortable getSortable(SORTABLE sortable) {
		return sortables.get(sortable);
	}
	
	public enum SORTABLE{
		
		SELECTION_SORT("SELECTION_SORT"), 
		INSERTION_SORT("INSERTION_SORT"), 
		BUBBLE_SORT("BUBBLE_SORT"), 
		QUICK_SORT("QUICK_SORT"), 
		MERGE_SORT("MERGE_SORT");
		
		private String name;
		
		private SORTABLE(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
}
