import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{

	private Item[] items;
	private int N;
	private int head;
	private int tail;
	
	public RandomizedQueue() {
    	items = (Item[]) new Object[1];
    	head = -1;
    	tail = 0;
    }

    public boolean isEmpty() {
    	return N == 0;
    }

    public int size() {
    	return N;
    }

    public void enqueue(Item item) {
    	
    	if(item == null)
    		throw new IllegalArgumentException();
    	
    	if(isEmpty())
    		head++;
    	
    	if(N == items.length) resize(2*N);
    	N++;
    	
    	if(tail == items.length)
    		tail = 0;
    	
    	items[tail++] = item;
    	
    }

	public Item dequeue() {
    	
    	if(isEmpty())
    		throw new NoSuchElementException();
		
		if(N == items.length/4) 
			resize(items.length/2); 
		
		int idx = StdRandom.uniform(N);
		N--;

		Item item = null;
		if(head < tail) {
			int del_idx = head + idx;
			item = items[del_idx];
			while(del_idx < tail - 1) {
				items[del_idx] = items[del_idx+1];
				del_idx++;
			}
			items[--tail] = null;
		}else {
			if(head + idx < items.length) {
				int del_idx = head + idx;
				item = items[del_idx];
				while(del_idx < items.length - 1) {
					items[del_idx] = items[del_idx+1];
					del_idx++;
				}
				items[items.length -1] = items[0];
				for(int i = 0; i < tail - 1; i++) {
					items[i] = items[i+1];
				}
				if(tail == 0) {
					tail = items.length - 1;
				}else {
					items[--tail] = null;
				}
					
			}else {
				int del_idx = head + idx - items.length;
				item = items[del_idx];
				while(del_idx < tail - 1) {
					items[del_idx] = items[del_idx+1];
					del_idx++;
				}
				items[--tail] = null;
			}
		}
		
		if(isEmpty()) {
			items = (Item[]) new Object[1];
			head = -1;
			tail = 0;
		}
		
		return item;
    }

    public Item sample() {
    	
    	if(isEmpty())
    		throw new NoSuchElementException();
    	
		int idx = StdRandom.uniform(N);
		
		Item item = null;
		if(head < tail) {
			int del_idx = head + idx;
			item = items[del_idx];
			while(del_idx < tail - 1) {
				items[del_idx] = items[del_idx+1];
				del_idx++;
			}
		}else {
			if(head + idx < items.length) {
				int del_idx = head + idx;
				item = items[del_idx];
				while(del_idx < items.length - 1) {
					items[del_idx] = items[del_idx+1];
					del_idx++;
				}
				items[items.length -1] = items[0];
				for(int i = 0; i < tail - 1; i++) {
					items[i] = items[i+1];
				}
					
			}else {
				int del_idx = head + idx - items.length;
				item = items[del_idx];
				while(del_idx < tail - 1) {
					items[del_idx] = items[del_idx+1];
					del_idx++;
				}
			}
		}
		return item;
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{

		@Override
		public boolean hasNext() {
			return !isEmpty();
		}

		@Override
		public Item next() {
			return dequeue();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
    	
    }
    
	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private void resize(int size) {

		Item[] resized_elements = (Item[]) new Object[size];
	
		int check = (head < tail) ? tail : items.length;
		int idx = 0;
		
		while(idx + head < check) {
			resized_elements[idx] = items[idx+head];
			idx++;
		}
		
		int i = 0;
		while(tail < head && i < tail) {
			resized_elements[idx+i] = items[i];
			i++;
		}
		
		items = resized_elements;
		head = 0;
		tail = idx+i;
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
//		queue.dequeue();
//		queue.dequeue();
		queue.enqueue(4);
		queue.enqueue(5);
//		queue.dequeue();
		
		
		for(int item : queue)
			System.out.println(item);
	}

}
