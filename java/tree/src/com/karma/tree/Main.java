package com.karma.tree;

import com.karma.tree.impl.BinaryTree;
import com.karma.tree.impl.NArrayTree;
import com.karma.tree.util.TreeTraversal;

public class Main {
	public static void main(String[] args) {
		/**
		 *         1
		 *         | 
		 *   2     3   4
		 *   |     |   |
		 * 5   6   7   8
		 * 
		 * PRE-ORDER
		 * 12563748
		 * POST-ORDER
		 * 56273841
		 * 
		 */


		NArrayTree<Integer> tree4 = new NArrayTree<Integer>();
		tree4.setValue(4);
		
		NArrayTree<Integer> tree8 = new NArrayTree<Integer>();
		tree8.setValue(8);
		
		tree4.addChild(tree8);

		NArrayTree<Integer> tree3 = new NArrayTree<Integer>();
		tree3.setValue(3);
		NArrayTree<Integer> tree7 = new NArrayTree<Integer>();
		tree7.setValue(7);
		tree3.addChild(tree7);
		
		
		NArrayTree<Integer> tree2 = new NArrayTree<Integer>();
		tree2.setValue(2);
		NArrayTree<Integer> tree5 = new NArrayTree<Integer>();
		tree5.setValue(5);
		NArrayTree<Integer> tree6 = new NArrayTree<Integer>();
		tree6.setValue(6);		
		tree2.addChild(tree5);
		tree2.addChild(tree6);
		
		NArrayTree<Integer> root = new NArrayTree<Integer>();
		root.setValue(1);
		
		root.addChild(tree2);
		root.addChild(tree3);
		root.addChild(tree4);
		
		System.out.println("====== PRE-ORDER ======");
		TreeTraversal.preOrder(root);
		System.out.println("\n====== POST-ORDER ======");
		TreeTraversal.postOrder(root);
		
		BinaryTree<Integer> left1 = new BinaryTree<Integer>();
		left1.setValue(2);
		left1.setLeftChild(null);
		left1.setRightChild(null);
		
		BinaryTree<Integer> left11 = new BinaryTree<Integer>();
		left11.setValue(4);
		left11.setLeftChild(null);
		left11.setRightChild(null);
		
		BinaryTree<Integer> right11 = new BinaryTree<Integer>();
		right11.setValue(5);
		right11.setLeftChild(null);
		right11.setRightChild(null);

		BinaryTree<Integer> right1 = new BinaryTree<Integer>();
		right1.setValue(3);
		right1.setLeftChild(left11);
		right1.setRightChild(right11);
		
		BinaryTree<Integer> broot = new BinaryTree<Integer>();
		broot.setParent(null);
		broot.setValue(1);
		broot.setLeftChild(left1);
		broot.setRightChild(right1);

		System.out.println("\n====== PRE-ORDER ======");		
		TreeTraversal.preOrder(broot);
		System.out.println("\n====== POST-ORDER ======");		
		TreeTraversal.postOrder(broot);
		System.out.println("\n====== IN-ORDER ======");		
		TreeTraversal.inOrder(broot);
		
	}
}
