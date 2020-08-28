package com.karma.analysis;

public class BinarySearch {
	
	public static Integer indexOf(Integer[] a, Integer k) {
		Integer lo = 0;
		Integer hi = a.length - 1;
		while(lo <= hi) {

			//Integer mid = lo + (hi - lo)/2;
			Integer mid = (lo + hi)/2;
			if(a[mid] == k)
				return mid;
			else if(a[mid] > k)
				hi = mid-1;
			else
				lo = mid+1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Integer[] arr = new Integer[]{5, 15, 20, 31, 40};
		System.out.println(indexOf(arr,5));
		System.out.println(indexOf(arr,15));
		System.out.println(indexOf(arr,20));
		System.out.println(indexOf(arr,31));
		System.out.println(indexOf(arr,40));
	}
}
