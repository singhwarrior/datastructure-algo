package com.karma.graph;

import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

public class GraphHelper {
	
	public static Boolean isBipartite(Graph g, int v) {
		Boolean[] marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		COLOUR[] coloured = Collections.nCopies(g.V(), null).toArray(new COLOUR[0]);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		marked[v] = true;
		coloured[v] = COLOUR.WHITE;
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int vertex = queue.remove();
			for(int adj:g.adj(vertex)) {
				if(marked[adj] && coloured[adj] == coloured[vertex]) {
					return false;
				}
				if(!marked[adj]) {
					marked[adj] = true;
					coloured[adj] = complement(coloured[vertex]);
					queue.add(adj);
				}
			}
		}
		return true;
	}
	
	public static Boolean isBipartite_dfs(Graph g, int v) {
		Boolean[] marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		COLOUR[] coloured = Collections.nCopies(g.V(), null).toArray(new COLOUR[0]);
		
		coloured[v] = COLOUR.WHITE;
		return dfs(g, v, marked, coloured);
		
	}
	
	private static Boolean dfs(Graph g, int v, Boolean[] marked, COLOUR[] coloured) {	
		marked[v] = true;
		for(int adj:g.adj(v)) {
			if(marked[adj] && coloured[adj] == coloured[v]) {
				return false;
			}
			if(!marked[adj]) {
				marked[adj] = true;
				coloured[adj] = complement(coloured[v]);
				if(!dfs(g, adj, marked, coloured)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static Boolean hasCycle(Graph g, Integer v, Boolean[] marked, Integer[] edgeTo) {
		marked[v] = true;
		for(Integer adj:g.adj(v)) {
			if(marked[adj] && v!=null && adj!=null && edgeTo[v] != adj)
				return true;
			if(!marked[adj]) {
				edgeTo[adj] = v;
				return hasCycle(g, adj, marked, edgeTo);
			}
		}
		return false;
	}
	
	private static COLOUR complement(COLOUR colour) {
		if(colour == COLOUR.WHITE)
			return COLOUR.RED;
		else
			return COLOUR.WHITE;
	}
	
	private enum COLOUR{
		WHITE, RED;
	}
	
	public static void main(String[] args) {
		Graph bipartite = new Graph(6);
		bipartite.addEdge(0, 4);
		bipartite.addEdge(0, 5);
		bipartite.addEdge(1, 4);
		bipartite.addEdge(2, 4);
		bipartite.addEdge(3, 5);
		
		System.out.println(bipartite);
		
		for(int v=0; v<bipartite.V(); v++) {
			System.out.println(isBipartite(bipartite, v));			
		}
		
		Graph not_bipartite = new Graph(6);
		not_bipartite.addEdge(0, 4);
		not_bipartite.addEdge(0, 5);
		not_bipartite.addEdge(1, 4);
		not_bipartite.addEdge(1, 2);
		not_bipartite.addEdge(2, 4);
		not_bipartite.addEdge(3, 5);
		
		for(int v=0; v<not_bipartite.V(); v++) {
			System.out.println(isBipartite(not_bipartite, v));			
		}
		
		System.out.println("*********************************");
		
		for(int v=0; v<bipartite.V(); v++) {
			System.out.println(isBipartite_dfs(bipartite, v));
		}
		
		for(int v=0; v<not_bipartite.V(); v++) {
			System.out.println(isBipartite_dfs(not_bipartite, v));			
		}
		
		System.out.println("**************** Has Cycle ************");
		
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		
		Boolean[] marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		Integer[] edgeTo = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		System.out.println(hasCycle(g, 0, marked, edgeTo));
		
		g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		//g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		
		marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		edgeTo = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		System.out.println(hasCycle(g, 0, marked, edgeTo));
		

	}

}
