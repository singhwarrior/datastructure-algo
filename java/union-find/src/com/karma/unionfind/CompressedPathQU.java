package com.karma.unionfind;

/**
 * 
 * CompressedPathQU is technique to improve the quick-union algorithm to next level.
 * WeightQuickUnion technique made the union and connected operations from O(N) to
 * O(log N). CompressedPathQU flattens the tree height and hence both Union(p,q) &
 * connected(p,q) functions can operate less than O(log N).
 * 
 * CompressedPathQU technique is applied on top of WeightQuickUnion which tries to
 * compress the paths of tree whenever union or connected functions are called. Both 
 * of them actually call root(i) function which does the operation of pointing objects 
 * to grand parents iteratively. Hence tree flattens every time when union(p,q) or 
 * connected(p,q) is called. Hence over a period of time the height of tree flattens
 * to log* N(i.e. log(log(..(log N))). 
 * 
 * @author singh_warrior
 *
 */
public class CompressedPathQU extends WeightQuickUnion{
	
	public CompressedPathQU(Integer N) {
		super(N);
	}
	
	public Integer root(Integer i) {
		while(i!=id[i]) {
			// i starts pointing to grandparent instead of its parent. 
			// It happens for all objects which come in path of i to the root.
			id[i] = id[id[i]]; 
			i = id[i];
		}
		return i;
	}
	
	public static void main(String[] args) {
		UF compressedPathQU = new CompressedPathQU(6);
		compressedPathQU.union(1, 5);  // Connecting objects 1 and 5
		compressedPathQU.union(2, 5);  // Connecting objects 2 and 5
		compressedPathQU.union(1, 0);  // Connecting objects 1 and 0
		
		System.out.println("Are objects 1, 0 are connected?:"+compressedPathQU.connected(1,0));
		System.out.println("Are objects 2, 0 are connected?:"+compressedPathQU.connected(2,0));
		
		compressedPathQU.union(3, 4);
		
		System.out.println("Are objects 1, 3 are connected?:"+compressedPathQU.connected(1,3));
	}

}
