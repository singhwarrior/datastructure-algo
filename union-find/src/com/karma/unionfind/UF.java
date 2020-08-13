package com.karma.unionfind;

/**
 * Union Find data type can be used to 
 * 
 * 1. Dynamically connect objects
 * 2. Check whether objects are connected to each other 
 * 
 * @author singh_warrior
 *
 */
public interface UF {
	void union(Integer p, Integer q);
	Boolean connected(Integer p, Integer q);
}
