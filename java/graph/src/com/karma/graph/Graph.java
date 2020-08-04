package com.karma.graph;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


public class Graph {
	
	private int V;
	private int E;
	private List<Integer>[] adj;
	
	public Graph(int V) {
		this.V = V;
		this.adj = (List<Integer>[])new List[V];
		for(int i=0; i<V; i++) {
			this.adj[i] = new LinkedList<Integer>();
		}
	}
	
	public Graph(InputStream in) {
		
	}
	
	public void validateVertex(int v) throws IllegalArgumentException{
		if(v < 0 || v >=V)
			throw new IllegalArgumentException("Given vertex ["+v+"] is not valid.");
	}
	
	public List<Integer> adj(int v){
		validateVertex(v);
		return this.adj[v];
	}
	
	/*
	 * Add edge to Graph.
	 */
	public void addEdge(int v, int w){
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<V; i++) {
			sb.append(i+" -> "+adj[i]+"\n");
		}
		return sb.toString();
	}

}
