package com.karma.tree.impl;

import java.util.LinkedList;
import java.util.List;

import com.karma.tree.Tree;

public class BinaryTree<T> implements Tree<T> {

	private Tree<T> parent;
	private Tree<T> leftChild;
	private Tree<T> rightChild;
	private T value;

	
	public void setParent(Tree<T> parent) {
		this.parent = parent;
	}

	public void setLeftChild(Tree<T> leftChild) {
		this.leftChild = leftChild;
		if(leftChild != null) {
			((BinaryTree<T>)this.leftChild).setParent(this);			
		}
	}

	public void setRightChild(Tree<T> rightChild) {
		this.rightChild = rightChild;
		if(rightChild != null) {
			((BinaryTree<T>)this.rightChild).setParent(this);			
		}
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public T content() {
		return value;
	}

	@Override
	public Tree<T> parent() {
		return parent;
	}

	@Override
	public Tree<T> leftChild() {
		return leftChild;
	}

	@Override
	public Tree<T> rightSibling() {
		return rightChild;
	}

	@Override
	public List<Tree<T>> children() {
		List<Tree<T>> childern = new LinkedList<Tree<T>>();
		if(leftChild != null)
			childern.add(leftChild);
		if(rightChild != null)
			childern.add(rightChild);
		return childern;
	}

	@Override
	public Tree<T> position() {
		return this;
	}

	@Override
	public Boolean isRoot() {
		return parent == null;
	}

	@Override
	public Boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

//	@Override
//	public Tree<T> findNode(Tree<T> node) {
//		
//		return null;
//	}

}
