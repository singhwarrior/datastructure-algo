package com.karma.tree;

import java.util.ArrayList;
import java.util.List;

import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoSuchKeyException;
import com.karma.tree.impl.BinarySearchTree;
import com.karma.tree.util.TreeTraversal;

/**
 * 					6 
 * 				  /   \ 
 * 				 1      10 
 * 				  \    /  \ 
 *  			   4  7     12 
 * 				  / \  \   /  \ 
 * 				 3   5  9 11   13 
 * 				/ 				 \ 
 * 			   2 				  15 
 * 									\ 
 * 									 18 
 * 									   \ 
 * 										19 
 * 										  \ 
 * 										  25 
 * 											\ 
 * 										    100
 * 
 * @author singg
 *
 */
public class MainBinarySearchTree {
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();		
		try {
			bst.insert(6);
			bst.insert(10);
			bst.insert(12);
			bst.insert(1);
			bst.insert(4);
			bst.insert(5);
			bst.insert(3);
			bst.insert(2);
			bst.insert(7);
			bst.insert(9);
			bst.insert(11);
			bst.insert(13);
			bst.insert(15);
			bst.insert(18);
			bst.insert(19);
			bst.insert(25);
			bst.insert(100);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		}

		
		List<Integer> all = new ArrayList<>();
		all.add(10);
		all.add(12);
		all.add(1);
		all.add(4);
		all.add(5);
		all.add(3);
		all.add(2);
		all.add(7);
		all.add(9);
		all.add(11);
		all.add(13);
		all.add(15);
		all.add(18);
		all.add(19);
		all.add(25);
		all.add(100);
		
		System.out.println("PRE-ORDER");
		TreeTraversal.preOrder(bst);
		System.out.println("\nPOST-ORDER");
		TreeTraversal.postOrder(bst);
		System.out.println("\nIN-ORDER");
		TreeTraversal.inOrder(bst);
		
		Tree<Integer> retnode;
		try {
			retnode = bst.search(100);
			if(retnode != null) {
				System.out.println("\n"+retnode.content());
				System.out.println("\n"+(retnode.content() == 100));
			}else {
				System.out.println("\nKey ["+100+"] does not exist!!");
			}
		} catch (NoSuchKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("MIN = "+bst.min().content()+" MAX = "+bst.max().content());
		
		try {
			System.out.println("Key =["+9+"], Predecessor = ["+((bst.predecessor(9) != null) ? bst.predecessor(9).content() : null)+"], Successor = ["+((bst.successor(9) != null) ? bst.successor(9).content() : null)+"]");
			System.out.println("Key =["+2+"], Predecessor = ["+((bst.predecessor(2) != null) ? bst.predecessor(2).content() : null)+"], Successor = ["+((bst.successor(2) != null) ? bst.successor(2).content() : null)+"]");
			System.out.println("Key =["+1+"], Predecessor = ["+((bst.predecessor(1) != null) ? bst.predecessor(1).content() : null)+"], Successor = ["+((bst.successor(1) != null) ? bst.successor(1).content() : null)+"]");
			System.out.println("Key =["+100+"], Predecessor = ["+((bst.predecessor(100) != null) ? bst.predecessor(100).content() : null)+"], Successor = ["+((bst.successor(100) != null) ? bst.successor(100).content() : null)+"]");
		} catch (NoSuchKeyException e) {
			e.printStackTrace();
		}		

		
		for(Integer del : all) {
			System.out.println("\nIN-ORDER AFTER DELETING "+del);
			try {
				bst.delete(del);
			} catch (NoSuchKeyException e) {
				e.printStackTrace();
			}
			TreeTraversal.inOrder(bst);
		}
		System.out.println("*************************");
		try {
			bst.delete(6);
		} catch (NoSuchKeyException e) {
			e.printStackTrace();
		}
		System.out.println("\nIN-ORDER AFTER DELETING 6 or root");
		TreeTraversal.inOrder(bst);
	}
}
