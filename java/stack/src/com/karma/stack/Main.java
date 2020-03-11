package com.karma.stack;

import com.karma.stack.exception.StackEmptyException;
import com.karma.stack.exception.StackFullException;
import com.karma.stack.impl.FixedSizeStack;

public class Main {
	public static void main(String[] args) {
		Stack<Integer> s = new FixedSizeStack<>();
		try {
			s.push(1);
			System.out.println(s.top().intValue());
			s.push(2);
			System.out.println(s.top().intValue());
			s.push(3);
			System.out.println(s.top().intValue());
			s.push(4);
			System.out.println(s.top().intValue());
			s.push(5);
			System.out.println(s.top().intValue());
			s.push(6);
			System.out.println(s.top().intValue());
			s.push(7);
			System.out.println(s.top().intValue());
			s.push(8);
			System.out.println(s.top().intValue());
			s.push(9);
			System.out.println(s.top().intValue());
			s.push(10);
			System.out.println(s.top().intValue());
			s.push(11);
			System.out.println(s.top().intValue());
		} catch (StackFullException | StackEmptyException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("=============================");
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
			s.pop();
			System.out.println(s.top().intValue());
		} catch (StackEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
