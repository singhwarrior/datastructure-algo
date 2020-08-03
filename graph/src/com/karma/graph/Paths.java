package com.karma.graph;

public interface Paths {
	Boolean hasPathTo(int v);
	Iterable<Integer> pathTo(int v);
}
