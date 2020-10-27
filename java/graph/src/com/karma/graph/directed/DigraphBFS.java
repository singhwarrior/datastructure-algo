package com.karma.graph.directed;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth First Search does the same in case of directed garph what it does
 * for undirected graph. For a given vertex, all of immediate neighbors 
 * are visited which are having direct edge from the vertex. Same happens 
 * for all other vertices as well. That is why BFS allows to solve the problem
 * of reachability of vertices from a given node one level to next level.
 * 
 * Hence BFS gets the shortest path of all reachable vertices from the given 
 * vertex.  
 * 
 * @author singhwarrior
 *
 */
public class DigraphBFS {
	
	int[] edgeTo;       // Maintains vertex from which directed edge to vertex represented by array index  
	boolean[] visited;  // Maintains vertex represented by array index is visited or not from source vertex.
	int[] distance;     // Maintains distance of vertex represented by array index from source vertex
	
	public DigraphBFS(Digraph g, int s) {
		edgeTo = new int[g.V()];
		
		for(int i=0; i<g.V(); i++)
			edgeTo[i] = -1;
		
		visited = new boolean[g.V()];
		distance = new int[g.V()];
		
		bfs(g, s);
	}
	
	/**
	 * BFS actual algorithm runs in this function. Queue data structure is used
	 * to do next level of scanning once the immediate neighbors are visited. At 
	 * the same time when neighbors are visited, the edgeTo & distance for 
	 * corresponding vertices is updated.
	 * 
	 * @param g
	 * @param s
	 */
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
	
	/**
	 * Once BFS scan is done for a given source vertex, isConnected(int v) function can be 
	 * called to check whether given vertex v is connected to source vertex or not. This
	 * operation happens in O(1) i.e. constant time.
	 * 
	 * @param v
	 * @return true/false whether vertex v is reachable from source vertex or not.
	 */
	public boolean isConnected(int v) {
		return visited[v];
	}
	
	/**
	 * Gives the minimum distance for a vertex v from source vertex. The distance
	 * is indeed the shortest path of vertex v from source vertex.
	 * 
	 * @param v
	 * @return distance of vertex v from source vertex after doing BFS
	 */
	public int distance(int v) {
		return distance[v];
	}
	
	/**
	 * This function returns the vertices which are part of shortest path from source vertex to vertex
	 * v. 
	 * 
	 * @param v
	 * @return Iterable containing vertices which are there in the path to vertex v from source vertex
	 */
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
