package com.karma.tree.util;

import java.util.ArrayList;
import java.util.List;

import com.karma.tree.Tree;
import com.karma.tree.impl.BinaryTree;

public class TreeTraversal {
	public static <T> void preOrder(Tree<T> tree) {
		if(tree != null) {
			System.out.print("["+tree.content()+"]");
			for(Tree<T> child :tree.children()) {
				preOrder(child);
			}			
		}
	}
	
	public static <T> void postOrder(Tree<T> tree) {
		if(tree != null) {
			for(Tree<T> child :tree.children()) {
				postOrder(child);
			}
			System.out.print("["+tree.content()+"]");			
		}
	}	

	public static <T> void inOrder(BinaryTree<T> tree) {
		if(tree != null) {
			inOrder((BinaryTree<T>)tree.leftChild());
			System.out.print("["+tree.content()+"]");
			inOrder((BinaryTree<T>)tree.rightSibling());			
		}
	}
	
	
	/**
	 *     1
	 *   2    3
	 * 4    5   6
	 *            7
	 * 1243567
	 * 4215367
	 *  
	 * @param preOrder
	 * @param inOrder
	 * @return
	 */
	public static <T> BinaryTree<T> prepareTreeFromPreAndInOrder(List<T> preOrder, List<T> inOrder){
		if(preOrder.size() == 0) return null;
		T root = preOrder.get(0);
		int index = inOrder.indexOf(root);
		
		List<T> leftPreOrder = new ArrayList<T>();
		List<T> rightPreOrder = new ArrayList<T>();
		List<T> leftInOrder = new ArrayList<T>();
		List<T> rightInOrder = new ArrayList<T>();
		
	
		for(int i = 1; i< preOrder.size(); i++) {
			if(i<=index) {
				leftPreOrder.add(preOrder.get(i));				
			}else {
				rightPreOrder.add(preOrder.get(i));
			}
		}
		
		for(int i = 0; i< inOrder.size(); i++) {
			if(i< index) {
				leftInOrder.add(inOrder.get(i));				
			}else if(i > index){
				rightInOrder.add(inOrder.get(i));
			}
		}
		
		BinaryTree<T> tree = new BinaryTree<>();
		tree.setValue(root);
		tree.setLeftChild(prepareTreeFromPreAndInOrder(leftPreOrder, leftInOrder));
		tree.setRightChild(prepareTreeFromPreAndInOrder(rightPreOrder, rightInOrder));
		return tree;
	}
	
	/**
	 *     1
	 *   2    3
	 * 4    5   6
	 *            7
	 * 4257631
	 * 4215367
	 *  
	 * @param preOrder
	 * @param inOrder
	 * @return
	 */
	public static <T> BinaryTree<T> prepareTreeFromPostAndInOrder(List<T> postOrder, List<T> inOrder){
		if(postOrder.size() == 0) return null;
		T root = postOrder.get(postOrder.size()-1);
		int index = inOrder.indexOf(root);
		
		List<T> leftPostOrder = new ArrayList<T>();
		List<T> rightPostOrder = new ArrayList<T>();
		List<T> leftInOrder = new ArrayList<T>();
		List<T> rightInOrder = new ArrayList<T>();
		
	
		for(int i = 0; i< postOrder.size() - 1; i++) {
			if(i<index) {
				leftPostOrder.add(postOrder.get(i));				
			}else {
				rightPostOrder.add(postOrder.get(i));
			}
		}
		
		for(int i = 0; i< inOrder.size(); i++) {
			if(i< index) {
				leftInOrder.add(inOrder.get(i));				
			}else if(i > index){
				rightInOrder.add(inOrder.get(i));
			}
		}
		
		BinaryTree<T> tree = new BinaryTree<>();
		tree.setValue(root);
		tree.setLeftChild(prepareTreeFromPostAndInOrder(leftPostOrder, leftInOrder));
		tree.setRightChild(prepareTreeFromPostAndInOrder(rightPostOrder, rightInOrder));
		return tree;
	}
	
}
