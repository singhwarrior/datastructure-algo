package com.karma.tree.impl;

import com.karma.tree.Tree;
import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoSuchKeyException;

/**
 * BST is an ordered binary tree which contains keys such that left sub tree
 * keys are always less than root key and root key is less than right sub tree.
 * 
 * Characteristics Search is O(H) where H is height. Or if shallow tree then
 * O(log n) Delete is O(H) where H is height. Or if shallow tree then O(log n)
 * Insert is best = O(n*log n), average = O(n*log n) and worst = O(n*n)
 * 
 * @author singg
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public void insert(T key) throws DuplicateKeyException{
		BinarySearchTree<T> keyNode = new BinarySearchTree<>();
		keyNode.setValue(key);
		if(this.content() == null) {
			this.setValue(key);
		}else if (this.content().compareTo(key) > 0) {
			if (this.leftChild() == null) {
				this.setLeftChild(keyNode);
			} else {
				((BinarySearchTree<T>) this.leftChild()).insert(key);
			}
		} else if(this.content().compareTo(key) < 0){
			if (this.rightSibling() == null) {
				this.setRightChild(keyNode);
			} else {
				((BinarySearchTree<T>) this.rightSibling()).insert(key);
			}
		} else if(this.content().compareTo(key) == 0) {
			throw new DuplicateKeyException("Key = "+key+" already exists.");
		}
	}

	public Tree<T> search(T key) throws NoSuchKeyException{
		if (this.content().compareTo(key) == 0)
			return this;
		if (this.content().compareTo(key) > 0) {
			if (this.leftChild() == null) {
//				return null;
				throw new NoSuchKeyException("Key = "+key+" does not exist.");
			} else {
				return ((BinarySearchTree<T>) this.leftChild()).search(key);
			}
		} else {
			if (this.rightSibling() == null) {
//				return null;
				throw new NoSuchKeyException("Key = "+key+" does not exist.");
			} else {
				return ((BinarySearchTree<T>) this.rightSibling()).search(key);
			}
		}
	}
	
	public Tree<T> min() {
		Tree<T> node = this;
		while (node.leftChild() != null) {
			node = node.leftChild();
		}
		return node;
	}

	public Tree<T> max() {
		Tree<T> node = this;
		while (node.rightSibling() != null) {
			node = node.rightSibling();
		}
		return node;
	}

	public Tree<T> successor(T key) throws NoSuchKeyException {
		Tree<T> targetNode = this.search(key);			
		BinarySearchTree<T> successor = null;
		BinarySearchTree<T> inNode = (BinarySearchTree<T>) targetNode;
		if (inNode.rightSibling() == null) {
			BinarySearchTree<T> parent = ((BinarySearchTree<T>) inNode.parent());
			while (parent != null && parent.rightSibling() == inNode) {
				inNode = parent;
				parent = (BinarySearchTree<T>) inNode.parent();
			}
			successor = parent;
		} else {
			successor = (BinarySearchTree<T>) ((BinarySearchTree<T>) inNode.rightSibling()).min();
		}
		return successor;
	}


	public Tree<T> predecessor(T key) throws NoSuchKeyException {
		Tree<T> targetNode = this.search(key);
		BinarySearchTree<T> predecessor = null;
		BinarySearchTree<T> inNode = (BinarySearchTree<T>) targetNode;
		if (inNode.leftChild() == null) {
			BinarySearchTree<T> parent = ((BinarySearchTree<T>) inNode.parent());
			while (parent != null && parent.leftChild() == inNode) {
				inNode = parent;
				parent = (BinarySearchTree<T>) inNode.parent();
			}
			predecessor = parent;
		} else {
			predecessor = (BinarySearchTree<T>) ((BinarySearchTree<T>) inNode.leftChild()).max();
		}
		return predecessor;
	}
	

	public void delete(T key) throws NoSuchKeyException {
		Tree<T> node = this.search(key);
		if (node.isLeaf()) {
			deleteLeafNode(node);
		} else if (node.leftChild() != null && node.rightSibling() == null) {
			BinarySearchTree<T> predecessor = (BinarySearchTree<T>) ((BinarySearchTree<T>) node).predecessor(key);
			((BinarySearchTree<T>) node).setValue(predecessor.content());
			predecessor.delete(predecessor.content());
		} else if (node.leftChild() == null && node.rightSibling() != null) {
			BinarySearchTree<T> successor = (BinarySearchTree<T>) ((BinarySearchTree<T>) node).successor(key);
			((BinarySearchTree<T>) node).setValue(successor.content());
			successor.delete(successor.content());
		} else {
			BinarySearchTree<T> successor = (BinarySearchTree<T>) ((BinarySearchTree<T>) node).successor(key);
			((BinarySearchTree<T>) node).setValue(successor.content());
			successor.delete(successor.content());
		}
	}

	private void deleteLeafNode(Tree<T> node) {
		if (node.isRoot()) {
			((BinarySearchTree<T>)node).setValue(null);
		} else {
			BinarySearchTree<T> targetNode = (BinarySearchTree<T>) node;
			BinarySearchTree<T> parentNode = (BinarySearchTree<T>) targetNode.parent();
				if (parentNode.leftChild() == targetNode) {
					parentNode.setLeftChild(null);
					targetNode = null;
				} else if (parentNode.rightSibling() == targetNode) {
					parentNode.setRightChild(null);
					targetNode = null;
				}
		}
	}

}
