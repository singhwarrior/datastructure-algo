package com.karma.tree;

import java.util.List;

public interface Tree<T> {
	public T content();
	public Tree<T> parent();
	public Tree<T> leftChild();
	public Tree<T> rightSibling();
	public List<Tree<T>> children();
	public Tree<T> position();
	public Boolean isRoot();
	public Boolean isLeaf();
//	public Tree<T> search(T key);
}
