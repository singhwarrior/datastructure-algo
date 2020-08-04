package com.karma.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsPaths implements Paths{

	private int sourceVertex;
	private Boolean[] marked;
	private Integer[] edgeTo;
	private Queue<Integer> queue;
	
	public BfsPaths(Graph g, int sourceVertex) {
		this.sourceVertex = sourceVertex;
		marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		edgeTo = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		queue = new LinkedList<Integer>();
		bfs(g, sourceVertex);
	}
	
	public Boolean hasPathTo(int v) {
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		List<Integer> path = new LinkedList<Integer>();
		int destination = v;
		while(edgeTo[destination] != null) {
			path.add(0, destination);
			destination = edgeTo[destination];
		}
		return path;
	}
	
	private void bfs(Graph g, int sourceVertex) {
		queue.add(sourceVertex);
		marked[sourceVertex] = true;
		while(!queue.isEmpty()) {
			Integer vertex = queue.remove();
			for(Integer adjVertex : g.adj(vertex)) {
				if(!marked[adjVertex]) {
					queue.add(adjVertex);
					marked[adjVertex] = true;
					edgeTo[adjVertex] = vertex;
				}
			}
		}
	}
	
	
	
	
	
}
