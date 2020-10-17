package com.karma.digraph;

/**
 * A directed graph which does not have any cycle is called 
 * DAG(Direct Acyclic Graph). A DAG is very important concept
 * which can be used for multiple applications where 
 * precedence of tasks(which is also called Topological Order). 
 * 
 * Hence this class TopologicalOrder gives the sequence of 
 * vertices in precedence order. First it is identified 
 * whether the given directed graph is DAG or not. If it is
 * a DAG the reversePostOrder sequence is given which makes 
 * sure the outcome represents topological order.
 * 
 * @author singhwarrior
 */
public class TopologicalOrder {
	private DirectedCycle directedCycle;
	private DigraphDfsOrder digraphDfsOrder;
	private Iterable<Integer> order;	
	
	public TopologicalOrder(Digraph g) {
		directedCycle = new DirectedCycle(g);
		
		if(!directedCycle.hasCycle()) {
			digraphDfsOrder = new DigraphDfsOrder(g);
			order = digraphDfsOrder.reversePostOrder();
		}
	}

	public boolean isDAG() {
		return order != null;
	}
	
	public Iterable<Integer> order(){
		return order;
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
		g.addEdge(7, 5);
		
		TopologicalOrder topology = new TopologicalOrder(g);
		
		for(int i : topology.order())
			System.out.print(i+" ");
			
	}
}
