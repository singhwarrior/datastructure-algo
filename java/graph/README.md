# Graph 

Graph data structure is a set of Edges and Vertices. It has following practical usage:

- Distributed computing as DAG
- Internet routing and connectivity between computers
- Social Network(Like Facebook, LinkedIn etc)
- Machine Learning Algorithm like Neural Networks etc

### Implementation

Adjacency List implementation has been used here. Which is much more cost effective compared to Adjacency Matrix or edges as list.

Index of array *adj* in *Graph* object represents each vertex and the content of array is a *List<Integer>* contains all vertices adjacent to current vertex.
 
```Java
private List<Integer>[] adj;
```

For adding an edge in a graph object following method is used

```Java
public void addEdge(int v, int w){
	validateVertex(v);
	validateVertex(w);
	adj[v].add(w);
	adj[w].add(v);
	E++;
}
```

### Helper Methods

Following helper method returns adjacent vertices to a given vertex.

```Java
public List<Integer> adj(int v){
	validateVertex(v);
	return this.adj[v];
}
```

Following helper method returns degree(number of edges attached) of a given vertex.

```Java
public int degree(int v) {
	validateVertex(v);
	return adj[v].size();
}
```

# Graph Traversal

Depth first search(DFS) and Breadth first Search(BFS) are the two traversal techniques has been used. Both can be used to identify whether a path exists between a source vertex and other vertices of the graph. 

To make implementation DFS and BFS, Paths interface has been used. *hasPathTo(int v)* tells whether there is a path between source and destination vertex or not. *pathTo(int v)* gives the path from source to destination. 

```Java
public interface Paths {
	Boolean hasPathTo(int v);
	Iterable<Integer> pathTo(int v);
}
```

Client application has to call specific implementation in following way to check the connectivity between source vertex and all destination vertex. Also we can get paths from source to all destination vertex.


```Java
Paths paths = new DfsPaths(g, 0);
for(int i=1; i<g.V(); i++) {
	System.out.println(dfsPaths.hasPathTo(i));
	System.out.println(dfsPaths.pathTo(i));
}

Paths bfsPaths = new BfsPaths(g, 0);
for(int i=1; i<g.V(); i++) {
	System.out.println(bfsPaths.hasPathTo(i));
	System.out.println(bfsPaths.pathTo(i));
}
```

### DFS

Depth first search is less efficient Graph Traversal Technique. Reason is total time taken to traverse is equal to sum of degrees of all vertices. Also the path returned from DFS is not guaranteed to be shortest path between source and destination node. 

The implementation here is recursive but it can be done using Stack as well.

```Java
private void dfs(Graph g, int vertex) {
	this.marked[vertex] = true;		
	for(int ver : g.adj(vertex)) {
		if(!marked[ver]) {
			this.marked[ver] = true;
			this.edgeTo[ver] = vertex; 
			dfs(g, ver);				
		}
	}
}
```

Properties:

- Time taken by DFS is sum of the degree of all nodes
- Once DFS is executed, whether two nodes are connected to each other can be identified in constant time
- Once DFS is executed, path between two nodes can be identified in linear time i.e. equal to path length
- Each and every vertex only visited only once

### BFS

Breadth first search is more efficient Graph Traversal Technique compared to DFS.

```Java
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
```
Properties:

- The path given by BFS is the shorted path between the vertices

# Connected Components

If a graph has many smaller disconnected graphs, Connect Components algorithm gives the nodes of graph which are connected to each other and for each connected graph provides an id. So we can see all nodes/vertices within a connected component of a graph. 

CCClient code shows how to run Connected Component algorithm by passing a graph as follows. Also after that we can get total count of connected components by calling count() on the cc object. 

```Java
CC cc = new CC(g);
System.out.println(cc.count());
```

Following code snippet in CCClient shows how can we get all vertices within a connected component.

```Java
for(int i=0; i<cc.count(); i++) {
	System.out.println(cc.verticesInCC(i));
}
```

On the other side following code snippet show how can we get the connected component each vertex belong to.

```Java
for(int v=0; v<g.V(); v++) {
	System.out.println(v+"-->"+cc.cc(v));
}
```




 