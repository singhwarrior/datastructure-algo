package com.karma.graph;

public class GraphClient {
	
	public static void main(String[] args) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(2, 1);
		
		System.out.println(g.adj(0));
		System.out.println(g);
		System.out.println(g.degree(0));
		System.out.println(g.degree(1));
		System.out.println(g.degree(2));
		System.out.println(g.degree(3));
		
	}

}
