package com.karma.unionfind;

/**
 * 
 * WeightedQuickUnion is improvement to QuickUnion. In QuickUnion class
 * root(p) is directly added as child of q when union operation is called.
 * But in case of WeightedQuickUnion size of tree is maintained at root level.
 * And only the smaller sized tree is added to root of bigger sized tree.
 * Benefit of above mentioned technique is the height is balanced for a tree.
 * That means tree becomes flattened and hence both union(p, q) and 
 * connected(p,q) which uses root(i) function, the traversal become faster.
 * 
 * It can be proven that the height of the tree is less than log N on base 2. 
 * 
 * 
 * @author singh_warrior
 *
 */
public class WeightQuickUnion implements UF{

	private Integer[] id;
	private Integer[] sz;
	
	public WeightQuickUnion(Integer N) {
		id = new Integer[N];
		sz = new Integer[N];
		for(int i=0; i<N; i++) { 
			id[i] = i;
			sz[i] = 1;
		}
		
	}
	
	private Integer root(Integer i) {
		while(i != id[i]) i=id[i];
		return i;
	}
	
	@Override
	public void union(Integer p, Integer q) {
		
		Integer i = root(p);   // root of tree where p belongs
		Integer j = root(q);   // root of tree where q belongs
		if(i != j)             // If p and q are not already connected
			if(sz[i] >= sz[j]) {    // If size of p's tree is more than size of q's tree.
				id[j] = i; sz[i] += sz[j];
			}else {
				id[i] = j; sz[j] += sz[i]; 
			}
	}
	
	@Override
	public Boolean connected(Integer p, Integer q) {
		return root(p) == root(q);
	}
	
	public static void main(String[] args) {
		UF wQU = new WeightQuickUnion(6);
		wQU.union(1, 5);  // Connecting objects 1 and 5
		wQU.union(2, 5);  // Connecting objects 2 and 5
		wQU.union(1, 0);  // Connecting objects 1 and 0
		
		System.out.println("Are objects 1, 0 are connected?:"+wQU.connected(1,0));
		System.out.println("Are objects 2, 0 are connected?:"+wQU.connected(2,0));
		
		wQU.union(3, 4);
		
		System.out.println("Are objects 1, 3 are connected?:"+wQU.connected(1,3));
	}
}
