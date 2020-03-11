package com.karma.tree;

import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoSuchKeyException;
import com.karma.tree.impl.AvlTree;
import com.karma.tree.util.TreeTraversal;

/**
 * Same insertion order followed as in case of BST. But in this case
 * height shrinked to 5 for 17 keys which was 9 for BST.
 * 
 * 							 6
 * 						  /    \
 * 						4       15
 * 					  /	 \     /   \
 *                   2    5   10     19
 *                  / \      /  \    / \
 *                 1   3    7    12 18  25
 *                           \   / \      \
 *                            9 11  13     100
 *                            
 * @author singg
 *
 */
public class MainAvlTree {

	public static void main(String[] args) {
		AvlTree<Integer> avl = new AvlTree<>();
		try {
			avl.insert(6);
			avl.insert(10);
			avl.insert(12);
			avl.insert(1);
			avl.insert(4);
			avl.insert(5);
			avl.insert(3);
			avl.insert(2);
			avl.insert(7);
			avl.insert(9);
			avl.insert(11);
			avl.insert(13);
			avl.insert(15);
			avl.insert(18);
			avl.insert(19);
			avl.insert(25);
			avl.insert(100);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}


		System.out.println("PRE-ORDER");
		TreeTraversal.preOrder(avl);
		System.out.println("\nIN-ORDER");
		TreeTraversal.inOrder(avl);
		System.out.println("\nPOST-ORDER");
		TreeTraversal.postOrder(avl);
		
		System.out.println("\nMIN = "+avl.min().content());
		System.out.println("MAX = "+avl.max().content());
		try {
			System.out.println("SUCCESSOR OF 3 = "+avl.successor(3).content());
			System.out.println("SUCCESSOR OF 25 = "+avl.successor(25).content());
			System.out.println("PREDECESSOR OF 25 = "+avl.predecessor(25).content());
		} catch (NoSuchKeyException e) {
			e.printStackTrace();
		}

		try {
			avl.delete(13);
			avl.delete(5);
			avl.delete(10);
			avl.delete(3);
			avl.delete(100);
			avl.delete(6);
			avl.delete(100);
		} catch (NoSuchKeyException e) {
			System.out.println(e.getMessage()+e);
		}
		
		System.out.println("\nPRE-ORDER");
		TreeTraversal.preOrder(avl);
		System.out.println("\nIN-ORDER");
		TreeTraversal.inOrder(avl);
		
	}

}
