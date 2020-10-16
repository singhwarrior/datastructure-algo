package com.karma.digraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdIn {
	
	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		
//		
//		System.out.println("Enter number of vertices:");
//		String input = in.readLine();
//		int v = Integer.parseInt(input);
//		Graph graph = new Graph(v);
//		
//		do {
//			System.out.println("Enter node pair by space:");
//			input = in.readLine();
//			if(!"".equals(input)) {
//				String[] pair = input.split(" ");
//				graph.addEdge(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
//				System.out.println(input);
//			}
//		}while(!"".equals(input)); 
//		
//		System.out.println(graph.toString());
//		in.close();
		
		int[] test = new int[5];
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}

}
