package com.karma.digraph;

import java.util.LinkedList;
import java.util.List;

public class DigraphDFS {
	
	private int[] edgeTo;
	private boolean visited[];
	
	public DigraphDFS(Digraph g, int s) {
		edgeTo = new int[g.V()];
		visited = new boolean[g.V()];
		
		for(int i = 0; i < g.V(); i++) {
			edgeTo[i] = -1;
		}
		dfs(g, s);
	}
	
	private void dfs(Digraph g, int s) {
		visited[s] = true;
		for(int adj : g.adj(s)) {
			if(!visited[adj]) {
				edgeTo[adj] = s;
				dfs(g, adj);
			}
		}
	}
	
	public boolean isConnected(int v) {
		return visited[v];
	}
	
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
