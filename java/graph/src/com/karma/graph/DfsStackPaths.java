package com.karma.graph;

import java.util.Collections;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;

public class DfsStackPaths implements Paths{

	private Integer sourceVertex;
	private Boolean[] marked;
	private Integer[] edgeTo;
	
	public DfsStackPaths(Graph g, int v) {
		// Initialise
		sourceVertex = v;
		marked = Collections.nCopies(g.V(), false).toArray(new Boolean[0]);
		edgeTo = Collections.nCopies(g.V(), null).toArray(new Integer[0]);
		dfs(g,v);
	}
	
	@Override
	public Boolean hasPathTo(int v) {
		return marked[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		List<Integer> path = new LinkedList<Integer>();
		Integer fromVertex = v;
		
		while(fromVertex != null) {
			path.add(0, fromVertex);
			fromVertex = edgeTo[fromVertex];
		}
		return path;
	}
	
	private void dfs(Graph g, int v) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(v);
		Integer prev = null;
		while(!stack.isEmpty()) {
			Integer curr = stack.pop();
			if(!marked[curr]) {
				marked[curr] = true;
				edgeTo[curr] = prev;
				for(Integer adj : g.adj(curr)) {
					if(!marked[adj]) {
						stack.push(adj);
						stack.push(curr);
					}
				}				
			}
			prev = curr;
		}
	}
	
}
