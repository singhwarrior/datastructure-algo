package com.karma.pq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.karma.pq.exception.QueueEmptyException;
import com.karma.pq.exception.QueueFullException;
import com.karma.pq.impl.HeapPQ;
import com.karma.pq.impl.OrderedPQ;
import com.karma.pq.impl.UnorderedPQ;


public class Client {
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		Queue<Integer> unOrderedPQ = new UnorderedPQ<>(10);
		Queue<Integer> orderedPQ = new OrderedPQ<>(10);
		Queue<Integer> heapPQ = new HeapPQ<>(10);
		
		try {
			while(!(line = br.readLine()).equals("")) {
				Integer key = Integer.parseInt(line);
				
				unOrderedPQ.enqueue(key);
				orderedPQ.enqueue(key);
				heapPQ.enqueue(key);
				
			}
		} catch (IOException | QueueFullException e) {
			e.printStackTrace();
		}
		
		System.out.println("================================");
		while(!unOrderedPQ.isEmpty()) {
			try {
				System.out.print(unOrderedPQ.dequeue()+" ");
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
		
		
		System.out.println("================================");
		while(!orderedPQ.isEmpty()) {
			try {
				System.out.print(orderedPQ.dequeue()+" ");
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
		
		System.out.println("================================");
		while(!heapPQ.isEmpty()) {
			try {
				System.out.print(heapPQ.dequeue()+" ");
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
	}

}
