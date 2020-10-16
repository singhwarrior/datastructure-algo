package com.karma.digraph;

import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DigraphDfsOrder {

	private Queue<Integer> pre;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	private boolean[] visited;

	public DigraphDfsOrder(Digraph g) {
		// Initialization
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reversePost = new Stack<>();
		visited = new boolean[g.V()];
		for (int i = 0; i < g.V(); i++)
			if (!visited[i])
				dfs(g, i);

	}

	private void dfs(Digraph g, int v) {
		visited[v] = true;
		pre.add(v);
		for (int adj : g.adj(v)) {
			if (!visited[adj])
				dfs(g, adj);
		}

		post.add(v);
		reversePost.push(v);
	}

	public Iterable<Integer> preOrder() {
		return pre;
	}

	public Iterable<Integer> postOrder() {
		return post;
	}

	public Iterable<Integer> reversePostOrder() {
		List<Integer> reversePostList = new ArrayList<>();
		while (!reversePost.isEmpty()) {
			reversePostList.add(reversePost.pop());
		}
		return reversePostList;
	}

	public static void main(String[] args) {
		Digraph g = new Digraph(9);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(4, 8);
		g.addEdge(5, 3);
		g.addEdge(7, 5);

		DigraphDfsOrder dfsOrder = new DigraphDfsOrder(g);
		for (int i : dfsOrder.preOrder())
			System.out.print(i + " ");
		System.out.println();
		for (int i : dfsOrder.postOrder())
			System.out.print(i + " ");
		System.out.println();
		for (int i : dfsOrder.reversePostOrder())
			System.out.print(i + " ");

		System.out.println();
		
		g = new Digraph(9);
		g.addEdge(7, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 6);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(4, 8);
//		g.addEdge(5, 3);     // Cycle is removed
		g.addEdge(0, 5);

		dfsOrder = new DigraphDfsOrder(g);
		for (int i : dfsOrder.preOrder())
			System.out.print(i + " ");
		System.out.println();
		for (int i : dfsOrder.postOrder())
			System.out.print(i + " ");
		System.out.println();
		for (int i : dfsOrder.reversePostOrder())
			System.out.print(i + " ");
	}

}
