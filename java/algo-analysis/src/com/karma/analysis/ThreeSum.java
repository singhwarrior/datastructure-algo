package com.karma.analysis;

import java.util.Arrays;

/**
 * 
 * Three sum is just an extension of Two Sum problem. 
 * 
 * threeSum1(int[] a) solves the problem in O(N^3)
 * threeSum2(int[] a) splves the problem in O(N^2 * log N)
 * 
 * @author singh_warrior
 *
 */
public class ThreeSum {

	public static int threeSum1(int a[]) {
		int count = 0;
		for(int i=0; i<a.length-2; i++)             
			for(int j=i+1; j<a.length-1; j++)      
				for(int k=j+1; k<a.length; k++)     
					if(a[i]+a[j]+a[k] == 0) {
						count++;
						System.out.println("("+a[i]+","+a[j]+","+a[k]+")");
					}
		return count;
	}
	
	/**
	 * 1. Do Merge Sort of Array
	 * 2. Use three index i, j, k
	 * 3. 'i' will increase in outer loop while 'j' will always start 
	 *    with next element from 'i' and 'k' starts from last element 
	 *    of array
	 * 4. For every 'i', j and k will will keep on increasing and 
	 *    decreasing until j and k overlaps. Increment and decrement 
	 *    of j and k for a given i happens based on some conditions.
	 *    Conditions are indicated in algorithm.
	 * 5. For a given i when j and k overlaps then i is incremented
	 *    followed by j again pointing to i+1 and k pointing to last 
	 *    element of array.
	 *      
	 * @param a
	 * @return count
	 */
	
	public static int threeSum2(int a[]) {
		Arrays.sort(a);   // Merge Sort which will take O(N*log N)
		int count = 0;
		int i = 0;
		int j = i+1;
		int k = a.length -1;
		
		
		while(a[i] < 0) {     // If a[i] is greater than zero then there is no way 3-sum will be zero
			while(j < k)
				if(a[i]+a[j]+a[k] > 0)
					k--;
				else if(a[i]+a[j]+a[k] < 0)	
					j++;
				else {
					count++;
					System.out.println("("+a[i]+","+a[j]+","+a[k]+")");
					
					while(j <= a.length-1 && a[j] == a[j+1])  // Ignore unique from right
						j++;
					
					while(k >= 0 && a[k] == a[k-1])  // Ignore unique from left  
						k--;
					
					j++;
					k--;
				}
			while(i <= a.length-1 && a[i] == a[i+1]) // To ignore unique entries
				i++; 
			i++;
			j=i+1;
			k = a.length-1;
		}			
		return count;
	}
	
	public static void main(String[] args) {
		int[] in = new int[] {-4, 3, -2, 1, 0, -1, 2, 5, 4, 4, -4};
		
		System.out.println(threeSum1(in));
		System.out.println("=============================");
		System.out.println(threeSum2(in));
	}
	
}
