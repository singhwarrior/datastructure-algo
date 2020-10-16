package com.karma.digraph;

/**
 * 
 * 
 * @author singhwarrior
 *
 */
public class StronglyCC {
	
	private int[] scc;
	private boolean[] visited;
	private int count = 0;
	
	public StronglyCC(Digraph g) {
		scc = new int[g.V()];
		visited = new boolean[g.V()];
		
		DigraphDfsOrder dfsOrder = new DigraphDfsOrder(g.reverse());
		for(int v : dfsOrder.reversePostOrder()) {
			if(!visited[v]) {
				dfs(g, v);
				count++;
			}
		}
	}
	
	private void dfs(Digraph g, int v) {
		visited[v] = true;
		scc[count] = count;

		for(int adj : g.adj(v)) 
			if(!visited[adj]) 
				dfs(g, adj);
				
	}
	
	public boolean connected(int v, int w) {
		return scc[v] == scc[w];
	}
	
	public int id(int v) {
		return scc[v];
	}
	
	public int count() {
		return count+1;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(13);
		g.addEdge(0, 6);
		g.addEdge(0, 2);
		g.addEdge(1, 0);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 2);
		g.addEdge(3, 4);
		g.addEdge(4, 6);
		g.addEdge(4, 5);
		g.addEdge(4, 11);
		g.addEdge(5, 0);
		g.addEdge(5, 3);
		g.addEdge(6, 7);
		g.addEdge(7, 8);
		g.addEdge(8, 7);
		g.addEdge(9, 6);
		g.addEdge(9, 8);
		g.addEdge(9, 12);
		g.addEdge(10, 9);
		g.addEdge(11, 9);
		g.addEdge(12, 11);
		g.addEdge(12, 10);
		
		StronglyCC scc = new StronglyCC(g);
		System.out.println(scc.count());
	}
	
}
