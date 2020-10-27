package com.karma.graph.directed;

import java.util.LinkedList;
import java.util.List;

/**
 * Digraph represents Directed Graph data structure.
 *   
 * @author singhwarrior
 *
 */
public class Digraph {
	
	private int V; // Number of vertices directed graph
	private List<Integer>[] adj;
	private int E;
	
	public Digraph(int v) {
		this.V = v;
		this.adj = (List<Integer>[]) new List[this.V];
		for(int i = 0; i < this.V; i++) {
			this.adj[i] = new LinkedList<>();
		}
	}
	
	public int V() {
		return this.V;
	}
	
	public int E() {
		return this.E;
	}
	
	/**
	 * This function is used to construct the graph. For adding a directed
	 * edge from vertex v to vertex w this function adds an edge in adjacency
	 * list. As soon as the adge is added, the number of edges is increamented 
	 * buy 1.
	 * 
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		this.adj[v].add(w);
		E++;
	}
	
	/**
	 * Returns the Iterable elements of vertices for the target 
	 * vertex v.
	 * 
	 * @param v
	 * @return All adjacent vertices of target vertex v.
	 */
	public Iterable<Integer> adj(int v){
		return this.adj[v];
	}
	
	/**
	 * Returns number of edges going outwards of the vertex v. 
	 * If there is no outward edge it will return 0. 
	 * 
	 * @param v
	 * @return Out degree of the vertex v
	 */
	public int outDegree(int v) {
		return this.adj[v].size();
	}
	
	/**
	 * Returns number of inward edges to the target vertex. If no
	 * inwards vertex then size will be returned 0. Following logic
	 * takes O(V+E) time for getting inward edges of the target vertex
	 * v.
	 * 
	 * To improve the performance we can also have a reverse
	 * adjacency list. 
	 * 
	 * @param v
	 * @return Number of inward edges.
	 */
	public int inDegree(int v) {
		int count = 0;
		for(int i = 0; i < this.V; i++) {
			if(i == v) continue;
			for(int adj : adj(i)) 
				if(adj == v) {
					count++;
					break;
				}
		}
		return count;
	}
	
	/**
	 * This function will change the direction of all edges. This is
	 * useful in one of the elegant algorithm called Kosaraju's Algorithm.
	 * 
	 * @return Directed Graph where all directed edges are reversed for original directed graph.
	 */
	public Digraph reverse() {
		Digraph reverse = new Digraph(this.V);
		
		for(int i = 0; i < this.V; i++) {
			for(int v : this.adj[i]) {
				reverse.addEdge(v, i);
			}
		}
		
		return reverse;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < V; i++) 
			for(int adj : adj(i))
				sb.append(i+"->"+adj+"\n");
		return sb.toString();
	}
}
