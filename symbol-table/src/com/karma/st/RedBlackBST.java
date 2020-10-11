package com.karma.st;

import java.util.HashSet;
import java.util.Set;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
	
	private class Node{
		Key key;
		Value value;
		Node left;
		Node right;
		int count;
		COLOR color;
		
		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private enum COLOR{ RED, BLACK }
	
	private Node root;
	
	public Value get(Key key) {
		Node n = search(root, key);
		Value v = (n == null) ? null : n.value;
		return v;
	}
	
	public void put(Key key, Value value) {
		root = insert(root, key, value);
		root.color = COLOR.BLACK;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	public int size() {
		return size(root);
	}
	
	public Key min() {
		Node n = min(root);
		Key key = (n == null) ? null : n.key;
		return key;
	}
	
	public Key max() {
		Node n = max(root);
		Key key = (n == null) ? null : n.key;
		return key;
	}
	
	public Key predecessor(Key key) {
		Node n = predecessor(root, key);
		Key pre = (n == null) ? null : n.key;
		return pre;
	}
	
	public Key successor(Key key) {
		Node n = successor(root, key);
		Key succ = (n == null) ? null : n.key; 
		return succ;
	}
	
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private Node search(Node node, Key key) {
		if(node == null)
			return null;
		
		if(node.key.compareTo(key) > 0) 
			node = search(node.left, key);
		else if(node.key.compareTo(key) < 0)
			node = search(node.right, key);
		
		return node;
	}
	
	private Node insert(Node node, Key key, Value value) {
		if(node == null) {
			Node n = new Node(key, value);
			n.color = COLOR.RED;
			return n;			
		}

		
		if(node.key.compareTo(key) > 0) {
			node.left = insert(node.left, key, value);
		}else if(node.key.compareTo(key) < 0) {
			node.right = insert(node.right, key, value);
		}else
			node.value = value;
		
		if(isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right)) node = flipColor(node);
		
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	private Node delete(Node node, Key key) {
		if(node == null) return null;
		
		int cmp = node.key.compareTo(key);
		
		if(cmp > 0) node.left = delete(node.left, key);
		else if(cmp < 0) node.right = delete(node.right, key);
		else 
			if(node.left == null) node = node.right;
			else if(node.right == null) node = node.left;
			else {
				Node t = node;
				node = min(t.right);
				node.left = t.left;
				node.right = t.right;
				deleteMin(t.right);
			}
			
		if(node != null)
			node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	private void deleteMin(Node node) {
		if(node == null) return;
		
		Node prev = node;
		Node curr = node;

		while(curr.left != null) {
			prev = curr;
			curr = curr.left;
		}
		
		prev.left = curr.right;
	}
	
	private Node min(Node node) {
		if(node == null) return null;
		
		Node n = node;
		Node l = null;
		while(n != null) {
			l = n;
			n = n.left;
		}
		
		return l;
	}
	
	private Node max(Node node) {
		if(node == null) return null;
		
		Node n = node;
		Node l = null;
		while(n != null) {
			l = n;
			n = n.right;
		}
		
		return l;
	}
	
	private Node predecessor(Node node, Key key) {
		if(node == null) return null;
		
		int cmp = node.key.compareTo(key);
		
		if(cmp == 0) {
			return max(node.left);			
		}

		
		if(cmp > 0) 
			return predecessor(node.left, key);
		
		else if(cmp < 0) {
			Node t = predecessor(node.right, key);
			if(t != null)
				node = t;
		}
		
		return node;
	}
	
	private Node successor(Node node, Key key) {
		if(node == null) return null;
		
		int cmp = node.key.compareTo(key);
		
		if(cmp == 0) {
			node = min(node.right);
		}else if(cmp < 0) 
			return successor(node.right, key);
		else {
			Node t = successor(node.left, key);
			if(t != null)
				node = t;
		}
		
		return node;
	}
	
	
	public Set<Key> keySet(){
		Set<Key> set = new HashSet<>();
		preorder(root, set);
		return set;		
	}	
	
	private void preorder(Node node, Set<Key> set) {
		if(node == null) return;
		set.add(node.key);
		if(node.left != null) preorder(node.left, set);
		if(node.right != null) preorder(node.right, set);
	}
	
	private int size(Node node) {
		if(node == null) 
			return 0;
		
		return 1 + size(node.left) + size(node.right);
	}
	
	private int rank(Node node, Key key) {
		if(node == null) return 0;
		
		int cmp = node.key.compareTo(key);
		
		if(cmp == 0) 
			return size(node.left);
		else if(cmp > 0)
			return rank(node.left, key);
		else 
			return 1 + size(node.left) + rank(node.right, key);
	}
	
	private Node rotateLeft(Node n) {
		Node right = n.right;
		n.right = right.left;
		right.left = n;
		right.color = COLOR.BLACK;
		n.color = COLOR.RED;
		return right;
	}
	
	private Node rotateRight(Node n) {
		Node left = n.left;
		n.left = left.right;
		left.right = n;
		left.color = COLOR.BLACK;
		n.color = COLOR.RED;
		return left;
	}
	
	private Node flipColor(Node n) {
		n.left.color = COLOR.BLACK;
		n.right.color = COLOR.BLACK;
		n.color = COLOR.RED;
		return n;
	}
	
	private boolean isRed(Node n) {
		if(n == null) return false;	
		return n.color == COLOR.RED;
	}
	
	public static void main(String[] args) {
		RedBlackBST<Integer, String> st = new RedBlackBST<>();
		st.put(1, "A");
		st.put(3, "C");
		st.put(26, "Z");
		st.put(2, "B");
		st.put(4, "D");
		
		
		for(Integer key : st.keySet()) {
			System.out.println(key+"->"+st.get(key));
			System.out.println("PREDECESSOR="+key+"->"+st.predecessor(key));
			System.out.println("SUCCESSOR="+key+"->"+st.successor(key));
			System.out.println("RANK="+key+"->"+st.rank(key));
		}
		
		System.out.println("PREDECESSOR="+5+"->"+st.predecessor(5));
		System.out.println("SUCCESSOR="+5+"->"+st.successor(5));
		
		
		System.out.println("MIN="+st.min()+"->"+st.get(st.min()));
		System.out.println("MAX="+st.max()+"->"+st.get(st.max()));

		System.out.println("SIZE="+st.size());
		st.delete(26);
		System.out.println("SIZE="+st.size());
		st.delete(2);
		System.out.println("SIZE="+st.size());
		st.delete(1);
		System.out.println("SIZE="+st.size());
		st.delete(4);
		System.out.println("SIZE="+st.size());
		st.delete(3);
		System.out.println("SIZE="+st.size());
	}


}
