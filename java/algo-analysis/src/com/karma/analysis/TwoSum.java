package com.karma.analysis;

import java.util.Arrays;

/**
 * 
 * Example class which implements function for 2-Sum problem.
 * 
 * Function twoSum1 looks simple but running time of this algorithm 
 * is O(N^2). 
 * 
 * The same problem has been solved by twoSum2 function which works
 * in O(N*log N) time. It first sorts array which happens in N*log N
 * time as well as it does a linear time operation as well.
 * Hence twoSum2 running time is O(N*log N). This algorithm can 
 * efficiently work with duplicates as well.
 * 
 * @author singh_warrior
 *
 */
public class TwoSum {
	
	public static int twoSum1(int[] a) {
		int count = 0;
		for(int i=0; i<a.length-1; i++)
			for(int j=i+1; j<a.length; j++)
				if(a[i]+a[j] == 0) {
					System.out.println("["+a[i]+","+a[j]+"]");
					count++;
				}
		return count;
	}
	
	public static int twoSum2(int[] a) {
		Arrays.sort(a);
		int count = 0;
		int i = 0;
		int j = a.length - 1;
		while(i < j && a[i] < 0)
			if(Math.abs(a[i]) < Math.abs(a[j]))
				j--;
			else if(Math.abs(a[i]) > Math.abs(a[j]))
				i++;
			else {
				System.out.println("["+a[i]+","+a[j]+"]");
				count++;
				
				// If uniqueness not required, following two loops are not required
				while(j >= 0 && a[j] == a[j-1])  // Ignore unique from right  
					j--;
				
				while(i <= a.length-1 && a[i] == a[i+1])  // Ignore unique from left
					i++;				
				
				j--;
				i++;
			}
		return count;
	}
	
	public static void main(String[] args) {
		int[] in = new int[] {-4, 3, -2, 1, 0, -1, 2, 5, 4,4,-4};
		
		System.out.println(twoSum1(in));
		System.out.println("=======================");
		System.out.println(twoSum2(in));
	}
}
