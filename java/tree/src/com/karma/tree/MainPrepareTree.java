package com.karma.tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.karma.tree.impl.BinaryTree;
import com.karma.tree.util.TreeTraversal;

public class MainPrepareTree {

	public static void main(String[] args) throws IOException {

		final int NEW_LINE = (int) '\n';
		final int CHOICE0 = (int) '0';
		final int CHOICE1 = (int) '1';
		// int CHOICE2 = (int) '2';

		List<Integer> preOrder = new ArrayList<>();
		List<Integer> postOrder = new ArrayList<>();
		List<Integer> inOrder = new ArrayList<>();

		StringBuilder line = null;

		InputStream in = System.in;
		while (true) {
			System.out.println("*****************************************************");
			System.out.println("Enter 0 for PRE-ORDER and IN-ORDER input\n"
					+ "Enter 1 for POST-ORDER and IN-ORDER input\n" + "Enter 2 to exit");
			System.out.println("*****************************************************");
			System.out.println("Enter Choice");
			int choice = in.read();
			switch (choice) {
			case CHOICE0:
				int i = in.read(); // Initialize i
				System.out.println("PRE-ORDER:");
				line = new StringBuilder();
				while ((i = in.read()) != NEW_LINE) {
					line.append((char) i);
				}
				String[] preOrderArray = line.toString().split(",");
				for (String s : preOrderArray) {
					preOrder.add(new Integer(s));
				}
				line = new StringBuilder();
				System.out.println("IN-ORDER:");
				while ((i = in.read()) != NEW_LINE) {
					line.append((char) i);
				}
				String[] inOrderArray = line.toString().split(",");
				for (String s : inOrderArray) {
					inOrder.add(new Integer(s));
				}
				line = null;

//				System.out.println(preOrder);
//				System.out.println(inOrder);
				Tree<Integer> tree = TreeTraversal.prepareTreeFromPreAndInOrder(preOrder, inOrder);
				System.out.println("PREORDER");
				TreeTraversal.preOrder(tree);
				System.out.println("INORDER");
				TreeTraversal.inOrder((BinaryTree<Integer>)tree);
				
				preOrder.clear();
				inOrder.clear();
				break;
			case CHOICE1:
				int j = in.read(); // Initialize i
				System.out.println("POST-ORDER:");
				line = new StringBuilder();
				while ((j = in.read()) != NEW_LINE) {
					line.append((char) j);
				}
				String[] postOrderArray = line.toString().split(",");
				for (String s : postOrderArray) {
					postOrder.add(new Integer(s));
				}
				System.out.println("IN-ORDER:");
				line = new StringBuilder();
				while ((j = in.read()) != NEW_LINE) {
					line.append((char) j);
				}
				String[] inOrderArray1 = line.toString().split(",");
				for (String s : inOrderArray1) {
					inOrder.add(new Integer(s));
				}
				line = null;

				Tree<Integer> tree2 = TreeTraversal.prepareTreeFromPostAndInOrder(postOrder, inOrder);
				System.out.println("POSTORDER");
				TreeTraversal.postOrder(tree2);
				System.out.println("INORDER");
				TreeTraversal.inOrder((BinaryTree<Integer>)tree2);
				
				postOrder.clear();
				inOrder.clear();
				break;
			default:
				System.exit(0);
				break;
			}

		}
		
		

	}
	
}
