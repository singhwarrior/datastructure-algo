package com.karma.digraph;

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
