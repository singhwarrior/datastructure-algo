package com.karma.graph;

public class PathsClients {
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 5);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		
		System.out.println(g);
		
		Paths dfsPaths = new DfsPaths(g, 0);
		for(int i=1; i<g.V(); i++) {
			System.out.println(dfsPaths.hasPathTo(i));
			System.out.println(dfsPaths.pathTo(i));
		}
		
		Paths dfsPathsfor5 = new DfsPaths(g, 5);
		for(int i=0; i<g.V()-1; i++) {
			System.out.println(dfsPathsfor5.hasPathTo(i));
			System.out.println(dfsPathsfor5.pathTo(i));
		}
		
		System.out.println("============================================");
		
		Paths bfsPaths = new BfsPaths(g, 0);
		for(int i=1; i<g.V(); i++) {
			System.out.println(bfsPaths.hasPathTo(i));
			System.out.println(bfsPaths.pathTo(i));
		}
		
		Paths bfsPaths5 = new BfsPaths(g, 5);
		for(int i=0; i<g.V()-1; i++) {
			System.out.println(bfsPaths5.hasPathTo(i));
			System.out.println(bfsPaths5.pathTo(i));
		}
	}
}
