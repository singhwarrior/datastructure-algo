package com.karma.tree.impl;

import java.util.LinkedList;
import java.util.List;

import com.karma.tree.Tree;

public class NArrayTree<T> implements Tree<T> {

	private Tree<T> parent;
	private Tree<T> leftChild;
	private Tree<T> rightSibling;
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public Tree<T> parent() {
		return parent;
	}

	@Override
	public List<Tree<T>> children() {
		List<Tree<T>> children = new LinkedList<Tree<T>>();
		Tree<T> leftChild = this.leftChild;
		while (leftChild != null) {
			children.add(leftChild);
			leftChild = leftChild.rightSibling();
		}
		return children;
	}

	@Override
	public Tree<T> leftChild() {
		return leftChild;
	}

	@Override
	public Tree<T> rightSibling() {
		return rightSibling;
	}

	public void addChild(Tree<T> child) {
		((NArrayTree<T>)child).parent = this;
		
		if (isLeaf()) {
			this.leftChild = child;
		} else {
			Tree<T> currentChild = leftChild;
			Tree<T> nextChild = currentChild;
			while (nextChild != null) {
				currentChild = nextChild;
				nextChild = currentChild.rightSibling();
				if (nextChild == null) {
					((NArrayTree<T>) currentChild).rightSibling = child;
				}
			}
		}
	}

	@Override
	public Tree<T> position() {
		return this;
	}

	@Override
	public Boolean isRoot() {
		return parent == null && rightSibling == null;
	}

	@Override
	public Boolean isLeaf() {
		return leftChild == null;
	}

	@Override
	public T content() {
		return value;
	}

//	@Override
//	public Tree<T> findNode(Tree<T> node) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
