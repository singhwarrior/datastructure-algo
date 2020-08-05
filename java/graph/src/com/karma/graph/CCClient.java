package com.karma.graph;

public class CCClient {
	public static void main(String[] args) {
		Graph g = new Graph(10);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 6);
		g.addEdge(2, 5);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		g.addEdge(7, 8);
		g.addEdge(7, 9);
		System.out.println(g);
		
		CC cc = new CC(g);
		System.out.println(cc.count());
		
		for(int v=0; v<g.V(); v++) {
			System.out.println(v+"-->"+cc.cc(v));
		}
		
		for(int i=0; i<cc.count(); i++) {
			System.out.println(cc.verticesInCC(i));
		}
				
	}
}
