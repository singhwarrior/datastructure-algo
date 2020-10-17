package com.karma.digraph;

import java.util.LinkedList;
import java.util.List;

/**
 * DigraphDFS does the Depth First Search for a directed graph.
 * Using the Depth First Search, reachability problem can be 
 * solved from a given source vertex.   
 * 
 * @author singhwarrior
 *
 */
public class DigraphDFS {
	
	private int[] edgeTo;       // Maintains vertex from which directed edge to vertex represented by array index 
	private boolean visited[];  // Maintains vertex represented by array index is visited or not from source vertex.
	
	public DigraphDFS(Digraph g, int s) {
		// Initialization
		edgeTo = new int[g.V()];
		for(int i = 0; i < g.V(); i++) {
			edgeTo[i] = -1;
		}
		visited = new boolean[g.V()];
		
		dfs(g, s);
	}
	
	/**
	 * Depth first search gets called for a given vertex and not all 
	 * immediate neighbor vertices are visited but further one of the
	 * adjacent vertex and same for all vertices.
	 * 
	 * @param g
	 * @param s
	 */
	private void dfs(Digraph g, int s) {
		visited[s] = true;
		for(int adj : g.adj(s)) {
			if(!visited[adj]) {
				edgeTo[adj] = s;
				dfs(g, adj);
			}
		}
	}
	
	/**
	 * To check the connectivity between the given source vertex and any vertex
	 * v isConnected(int v) can be called which will return the result in form 
	 * of true or false in O(1) time.
	 * 
	 * @param v
	 * @return true if vertex v is reachable from source vertex, false otherwise
	 */
	public boolean isConnected(int v) {
		return visited[v];
	}
	
	/**
	 * This function can be called to identify the path from source vertex to 
	 * target vertex v. A linear time O(V) may take in worst case scenario for
	 * this to return the path. The path may change depending on underlying
	 * implementation of Directed Graph - Digraph.
	 * 
	 * @param v
	 * @return vertices as Iterable which is path from source vertex to target 
	 * 		   vertex 	 
	 */
	public Iterable<Integer> pathTo(int v){
		if(!visited[v]) return new LinkedList<>();
		List<Integer> path = new LinkedList<>();
		while(edgeTo[v] != -1) { 
			path.add(0, v);
			v = edgeTo[v];
		}
		
		path.add(0, v);
		return path;
	}
	
	public static void main(String[] args) {
		Digraph g = new Digraph(6);
		g.addEdge(0, 5);
		g.addEdge(1, 4); 
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 5);
		g.addEdge(3, 2);
		g.addEdge(4, 2);
		
		// Path or Minimum Spanning Tree created by DFS depends on underlying implementation
		// of graph but BFS will always give the shorted path.
		for(int i = 0; i < g.V(); i++) {
			for(int adj : g.adj(i))
				System.out.println(i +"->" + adj);
		}
		
		DigraphDFS dfs = new DigraphDFS(g, 1);
		for(int i = 0; i < g.V(); i++) {
			if(i == 1) continue;
			if(dfs.isConnected(i)) {
				for(int v : dfs.pathTo(i)) {
					System.out.print(v+" ");
				}
				System.out.println("");				
			}

		}
	}
}
