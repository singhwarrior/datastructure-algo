package com.karma.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CC {
	
	private Boolean[] marked;
	private Integer[] id;
	private int count;
	
	public CC(Graph g) {
		this.marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		this.id = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		
		for(int v=0; v<g.V(); v++) {
			if(!marked[v]) {
				dfs(g,v);
				count++;				
			}
		}
	}
	
	public int count() {
		return count;
	}
	
	public int cc(int vertex) {
		return id[vertex];
	}
	
	public Iterable<Integer> verticesInCC(int cc){
		List<Integer> vertices = new LinkedList<Integer>();
		for(int i=0; i<id.length; i++) {
			if(id[i] == cc) {
				vertices.add(i);
			}
		}
		return vertices;
	}
	
	public Boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	
	private void dfs(Graph g, int vertex) {
		marked[vertex] = true;
		id[vertex] = count;
		for(int adj : g.adj(vertex)) {
			if(!marked[adj]) { 
				id[adj] = count;
				dfs(g, adj);
			}
		}
	}

}
