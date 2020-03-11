package com.karma.tree;

import java.util.ArrayList;
import java.util.List;

import com.karma.tree.exception.DuplicateKeyException;
import com.karma.tree.exception.NoPredecessorException;
import com.karma.tree.exception.NoSuccessorException;
import com.karma.tree.exception.NoSuchKeyException;
import com.karma.tree.impl.TwoFourTree;

public class Main24Tree {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		System.out.println(list.size());
		list.add(2);
		System.out.println(list);
		list.add(1, 3);
		System.out.println(list);
		TwoFourTree<Integer> tree = new TwoFourTree<>();
		try {
			tree.insert(5);
			tree.insert(15);
			tree.insert(12);
			tree.insert(2);
			tree.insert(6);
			tree.insert(18);
			tree.insert(10);
			tree.insert(9);
			tree.insert(13);
			tree.insert(20);
			tree.insert(25);
			tree.insert(26);
			tree.insert(35);
			tree.insert(27);
			tree.insert(30);
			tree.insert(31);
			System.out.println("Minimum key = "+tree.min());
			System.out.println("Maximum key = "+tree.max());
			try {
				System.out.println("Predecessor of 2 = "+tree.predecessor(2));
			} catch (NoPredecessorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("Predecessor of 10 = "+tree.predecessor(10));
			} catch (NoPredecessorException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Predecessor of 35 = "+tree.predecessor(35));
			} catch (NoPredecessorException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Successor of 20 = "+tree.successor(20));
			} catch (NoSuccessorException e) {
				e.printStackTrace();
			}
			
			try {
				System.out.println("Successor of 35 = "+tree.successor(35));
			} catch (NoSuccessorException e) {
				e.printStackTrace();
			}
			
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
