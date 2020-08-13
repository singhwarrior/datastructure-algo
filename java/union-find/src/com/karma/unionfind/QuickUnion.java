package com.karma.unionfind;

/**
 * 
 * Quick Union is tree based implementation. 
 * 
 * union(p,q)
 * ----------
 * For connecting two objects p and q, both need to be kept in same tree.
 * Two make them part of same tree, root(p) function is used. 
 * 
 * root(p) gives the root of tree in which p belongs. If p is not connected 
 * to q already then q is made parent of root(p). 
 * 
 * This operation can take O(N) time.
 * 
 * connected(p,q)
 * --------------
 * If root of two objects are same that means they are connected.
 * It is O(N) time complexity.
 * 
 * @author singh_warrior
 *
 */
public class QuickUnion implements UF{
	
	private Integer[] id;
	
	public QuickUnion(Integer N) {
		id = new Integer[N];
		for(int i=0; i<N; i++) id[i] = i;
	}
	
	private Integer root(Integer i) {
		while(i != id[i]) i = id[i];
		return i;
	}
	
	@Override
	public void union(Integer p, Integer q) {
		if(!connected(p,q))
			id[root(p)] = root(q);
	}
	
	@Override
	public Boolean connected(Integer p, Integer q) {
		return root(p)==root(q);
	}
	
	public static void main(String[] args) {
		UF qu = new QuickUnion(6);
		qu.union(1, 5);  // Connecting objects 1 and 5
		qu.union(2, 5);  // Connecting objects 2 and 5
		qu.union(1, 0);  // Connecting objects 1 and 0
		
		System.out.println("Are objects 1, 0 are connected?:"+qu.connected(1,0));
		System.out.println("Are objects 2, 0 are connected?:"+qu.connected(2,0));
		
		qu.union(3, 4);
		
		System.out.println("Are objects 1, 3 are connected?:"+qu.connected(1,3));
	}

}
