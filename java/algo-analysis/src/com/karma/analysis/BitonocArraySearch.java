package com.karma.analysis;

public class BitonocArraySearch {
	
	public static int getMax(int[] a) {
		int lo = 0;
		int hi = a.length-1;
		int mid = (lo+hi)/2;
		
		while(lo <= hi) {
			if(mid == 0 || mid == a.length-1)
				return mid;
			if(a[mid] > a[mid-1] && a[mid] > a[mid+1])
				return mid;
			else if(a[mid] > a[mid-1] && a[mid] < a[mid+1])
				lo = mid+1;
			else 
				hi = mid-1;
			mid = (lo+hi)/2;
		}
		return mid;
		
	}
	
	public static int bsearch(int[] a, int key, int low, int high) {
		int lo = low;
		int hi = high;
		
		while(lo <= hi) {
			int mid = (lo+hi)/2;
			
			if(a[mid] == key)
				return mid;
			else if(a[mid] > key) 
				hi=mid-1;
			else 
				lo = mid+1;
			
		}
		return -1;
		
	}
	
	public static int inverse_bsearch(int[] a, int key, int low, int high) {
		int lo = low;
		int hi = high;
		
		while(lo <= hi) {
			int mid = (lo+hi)/2;
			
			if(a[mid] == key)
				return mid;
			else if(a[mid] < key) 
				hi=mid-1;
			else 
				lo = mid+1;
			
		}
		return -1;
		
	}
	
	public static int bitonicSearch(int[] a, int key) {
		int mid = getMax(a);
		int idx1 = bsearch(a, key, 0, mid);
		if(idx1 != -1) return idx1;
		int idx2 = inverse_bsearch(a, key, mid, a.length-1);
		if(idx2 != -1) 
			return idx2;
		else 
			return -1;
	}
	
	
	
	
	
	public static void main(String[] args) {
		int[] bitonic = new int[] {1,10,21,18,12,8,4,3};
		System.out.println(bitonicSearch(bitonic, 1));  // 0
		System.out.println(bitonicSearch(bitonic, 3));  // 7
		System.out.println(bitonicSearch(bitonic, 18)); // 3
		System.out.println(bitonicSearch(bitonic, 12)); // 4
		System.out.println(bitonicSearch(bitonic, 10)); // 1
		System.out.println(bitonicSearch(bitonic, 21)); // 2
		
	}

}
