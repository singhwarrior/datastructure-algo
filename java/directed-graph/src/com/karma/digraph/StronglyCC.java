package com.karma.digraph;

/**
 * Strongly Connected Components are part of directed graph in which
 * every vertex is reachable from every other vertex.
 * 
 * This is very important to understand how many connected components
 * in the graph are available. StronglyCC class can be used to check 
 * whether two vertices are connected to each-other. To identify the 
 * strongly connected components in a graph, is done in two steps:
 * 
 * 1. First reversal of Directed Graph is done. i.e. Direction of all
 * the edges is reverted. (Please check the README.md file to 
 * understand why it is done so.)
 * 2. DFS is done on the reverse postorder sequence of the graph 
 * reverted in step 1. 
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
	
	/**
	 * 
	 * @param v
	 * @param w
	 * @return true when the vertices are connected and 
	 * 		   belong to same connected component else false
	 */
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
