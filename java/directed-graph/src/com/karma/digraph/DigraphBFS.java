package com.karma.digraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DigraphBFS {
	
	int[] edgeTo;
	boolean[] visited;
	int[] distance;
	
	public DigraphBFS(Digraph g, int s) {
		edgeTo = new int[g.V()];
		
		for(int i=0; i<g.V(); i++)
			edgeTo[i] = -1;
		
		visited = new boolean[g.V()];
		distance = new int[g.V()];
		
		bfs(g, s);
	}
	
	private void bfs(Digraph g, int s) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s); //enqueue
		visited[s] = true;
		
		while(!q.isEmpty()) {
			int u = q.remove();
			for(int adj : g.adj(u)) {
				if(!visited[adj]) {
					edgeTo[adj] = u;
					visited[adj] = true;
					distance[adj] = distance[u] + 1;
					q.add(adj);
				}
			}
		}
	}
	
	public boolean isConnected(int v) {
		return visited[v];
	}
	
	public int distance(int v) {
		return distance[v];
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
		g.addEdge(1, 2);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(2, 5);
		g.addEdge(3, 2);
		g.addEdge(4, 2);
		
		System.out.println(g.toString());
		System.out.println(g.reverse().toString());
		
		DigraphBFS bfs = new DigraphBFS(g, 1);
		for(int i = 0; i < g.V(); i++) {
			if(i == 1) continue;
			System.out.println("Path exists from 1 to "+i +"="+ bfs.isConnected(i));
			for(int v : bfs.pathTo(i)) {
				System.out.print(v+" ");
			}
			System.out.println("distance of "+i +"="+ bfs.distance(i));
			System.out.println("");
		}
		
	}

}
