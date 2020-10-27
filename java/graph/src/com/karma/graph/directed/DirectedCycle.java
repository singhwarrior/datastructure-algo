package com.karma.graph.directed;

import java.util.Stack;

/**
 * DirectedCycle is a class by which we can identify the cycle in a Directed Graph. 
 * It can identify cycle in a given DirectedGraph(Digraph) in V+E time. Where V is
 * the number of vertices and E is the number of edges in the graph.
 * 
 * This can be used in multiple usecases like Topological Sorting or Precedence 
 * Ordering problems like Job Scheduling. Topological ordering cannot be done for a 
 * Digraph which is having Cycle. A Digraph which does not contain any cycle is 
 * called DAG(Directed Acyclic Graph). 
 * 
 * @author singhwarrior
 *
 */
public class DirectedCycle {
	
	private int[] edgeTo;
	private boolean[] visited;   // To check whether vertex has been already visited or not. 
	private boolean[] onStack;   // To check whether a cycle has been found.
	private Stack<Integer> cycle; // Contains the path of cycle.
	
	public DirectedCycle(Digraph g) {
		//Initialization of all variables
		edgeTo = new int[g.V()];
		visited = new boolean[g.V()];
		onStack = new boolean[g.V()];
		
		// dfs for all unvisited vertices is called. DFS is called
		// vertices it is quite possible that few vertices are not 
		// connected at all from a given vertex. 
		for(int i = 0; i < g.V(); i++) {
			if(!visited[i]) dfs(g, i);
		}
	}
	
	/**
	 * 
	 * This function is almost equivalent to a normal dfs but there are 
	 * few important differences as follows:
	 * 
	 * A given vertex v for which dfs(v) is called will be marked as 
	 * visited so that no further recursive dfs should be called. Which
	 * is a normal check needs to be done in case of dfs. But there is
	 * one another variable onStack is also marked true for the same 
	 * vertex v. The same vertex can be visited again if there is an 
	 * edge between a vertex w for which dfs(w) has been called but if 
	 * v is not part of a cycle then there will be no further adjacent 
	 * vertices for v to visit hence all dfs calls to adjacent will be
	 * returned and that time onStack will be made false, so that logic 
	 * should not misunderstood this vertex as part of cycle because it
	 * has been visited again. 
	 * 
	 * @param g
	 * @param v
	 */
	private void dfs(Digraph g, int v) {
		visited[v] = true;
		onStack[v] = true;
		
		for(int adj : g.adj(v)) {
			if(hasCycle()) return; 		// If cycle is identified then recursive call is short circuited.
			if(!visited[adj]) {
				edgeTo[adj] = v;
				dfs(g, adj);
			}else if(onStack[adj]) {  	// Cycle identified
				cycle = new Stack<>();
				while(edgeTo[v] != adj) {
					cycle.push(v);
					v = edgeTo[v];
				}

				cycle.push(adj);
				cycle.push(v);	
			}
		}
		
		onStack[v] = false;   
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(9);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(4, 8);
		g.addEdge(5, 3);
		g.addEdge(7, 5);
		
		System.out.println(g);
		DirectedCycle directedCycle = new DirectedCycle(g);
		System.out.println(directedCycle.hasCycle());
		if(directedCycle.hasCycle()) {
			for(int v : directedCycle.cycle()) System.out.println(v);
		}
	}

}
