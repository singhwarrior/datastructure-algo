import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	private int N;
	
    public Deque() {
    	first = new Node();
    	last = new Node();
    }
    
	private class Node{
		Item item;
		Node prev;
		Node next;
	}

    public boolean isEmpty() {
    	return N == 0;
    }

    public int size() {
    	return N;
    }

    public void addFirst(Item item) {
    	
    	if(item == null)
    		throw new IllegalArgumentException();
    	
    	Node node = new Node();
    	node.item = item;
    	
    	if(isEmpty()) {
    		last.next = node;
    		first.next = node;
    		node.prev = first;
    	}else {
    		node.next = first.next;
    		first.next = node;
    		node.next.prev = node;
    		node.prev = first;
    	}
    	
    	N++;
    }

    public void addLast(Item item) {
    	
    	if(item == null)
    		throw new IllegalArgumentException();
    	
    	Node node = new Node();
    	node.item = item;
    	
    	if(isEmpty()) {
    		first.next = node;
    		last.next = node;
    		node.prev = first;
    	}else {
    		last.next.next = node;
    		node.prev = last.next;
    		last.next = node;
    	}
    	
		N++;
    }

    public Item removeFirst() {
    	
    	if(isEmpty())
    		throw new NoSuchElementException();
    	
    	Node node = first.next;
    	Item item = node.item;
    	
    	if(N == 1) {
    		last.next = null;
    		first.next = null;
    	}else {
    		first.next = node.next;
    		node.next.prev = first;
    	}
    	
		node.next = null;
		node.prev = null;
		node = null;
		
		N--;
		
    	return item;
    }

    public Item removeLast() {
    	if(isEmpty())
    		throw new NoSuchElementException();
    	
    	Node node = last.next;
    	Item item = node.item;
    	
    	if(N == 1) {
    		first.next = null;
    		last.next = null;
    	}else {
    		node.prev.next = null;
    		last.next = node.prev;
    	}

    	
    	node.prev = null;
    	node = null;
    	
    	N--;
    	
    	return item;
    }
    
    private class DequeIterator implements Iterator<Item>{

		@Override
		public boolean hasNext() {
			return !isEmpty();
		}

		@Override
		public Item next() {
			return removeFirst();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
    	
    }
	
    @Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
    
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		
		
		// As Stack
		StdOut.println("===============STACK===============");
		for(int i = 1; i <= 10; i++) {
			deque.addFirst(i);
		}
		
		for(int item : deque)
			StdOut.println(item);

		// As Queue		
		StdOut.println("===============QUEUE===============");
		for(int i = 1; i <= 10; i++) {
			deque.addLast(i);
		}

		for(int item : deque)
			StdOut.println(item);
		
		// Again as Queue
		StdOut.println("===============Inverted Queue===============");
		for(int i = 1; i <= 10; i++) {
			deque.addFirst(i);
		}
		
		while(!deque.isEmpty()) {
			StdOut.println(deque.removeLast());
		}
		
	}

	

}
