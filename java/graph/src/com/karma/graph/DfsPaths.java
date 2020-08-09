package com.karma.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DfsPaths implements Paths{
	
	private int s;
	private Boolean[] marked;
	private Integer[] edgeTo;
	
	public DfsPaths(Graph g, int sourceVertex) {
		this.s = sourceVertex;
		this.marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		this.edgeTo = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		dfs(g, sourceVertex);
	}
	
	@Override
	public Boolean hasPathTo(int v) {
		return marked[v];
	}
	
	@Override
	public Iterable<Integer> pathTo(int v){
		List<Integer> path = new LinkedList<Integer>(); 
		if(hasPathTo(v)) {
			path.add(v);
			while(edgeTo[v] != null) {
				path.add(0, edgeTo[v]);
				v = edgeTo[v];
			}
		}
		return path;
	}
	
	private void dfs(Graph g, int vertex) {
		this.marked[vertex] = true;		
		for(int ver : g.adj(vertex)) {
			if(!marked[ver]) {
//				this.marked[ver] = true;
				this.edgeTo[ver] = vertex; 
				dfs(g, ver);				
			}
		}
	}
}
