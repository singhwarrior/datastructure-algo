package com.karma.unionfind;

/**
 * 
 * QF represents Quick Find implementation for Union Find data type.
 * 
 * @author singh_warrior
 *
 */
public class QF implements UF{
	
	private Integer[] id;
	
	public QF(int N) {
		id = new Integer[N];
		for(int i=0; i<N; i++) id[i] = i;
	}
	
	/**
	 * 
	 * Connects p to q by replacing id[p] to q. Also replace id of all objects
	 * which are pointing to id[p] need to be replaced by q.  
	 * 
	 * @param p
	 * @param q
	 */
	@Override
	public void union(Integer p, Integer q) {
		Integer v = id[p];
		for(int i=0; i<id.length; i++) 
			if(id[i] == v) 
				id[i] = q;
	}
	
	/**
	 * p and q are connected if they are connected to same object. 
	 * 
	 * @param p
	 * @param q
	 * @return 
	 */
	@Override
	public Boolean connected(Integer p, Integer q) {
		return id[p] == id[q];
	}
	
	public static void main(String[] args) {
		UF qf = new QF(6);
		qf.union(1, 5);  // Connecting objects 1 and 5
		qf.union(2, 5);  // Connecting objects 2 and 5
		qf.union(1, 0);  // Connecting objects 1 and 0
		
		System.out.println("Are objects 1, 0 are connected?:"+qf.connected(1,0));
		System.out.println("Are objects 2, 0 are connected?:"+qf.connected(2,0));
		
		qf.union(3, 4);
		
		System.out.println("Are objects 1, 3 are connected?:"+qf.connected(1,3));
	}
}
