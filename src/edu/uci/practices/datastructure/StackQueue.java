package edu.uci.practices.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author William
 */
public class StackQueue {

	/**
	 * p3.2
	 * 
	 * How would you design a stack which, in addition to push and pop, also has
	 * a function min which returns the minimum element? Push, pop and min
	 * should all operate in O(1) time.
	 */
	
	public static class MinStack {
		
		private static final int STACK_SIZE = 1024;
		private static final int MIN_SIZE = 512;
		private int[] stack = new int[STACK_SIZE];
		private int[] min = new int[MIN_SIZE];
		private int topIndex = -1;
		private int minIndex = -1;
		
		public void push(int i) {
			stack[++topIndex] = i;
			
			if (minIndex == -1) {
				minIndex = 0;
				min[0] = 0;
			} else {
				if (i < min[minIndex]) {
					min[++minIndex] = topIndex;
				}
			}
		}
		
		public int pop() {
			if (topIndex == -1)
				return Integer.MAX_VALUE;
			
			--topIndex;
			if (min[minIndex] > topIndex)
				--minIndex;
			return stack[topIndex];
		}
		
		public int min() {
			if (minIndex == -1)
				return Integer.MAX_VALUE;
			return stack[min[minIndex]];
		}
	}

	/**
	 * p3.1
	 * 
	 * Describe how you could use a single array to implement three stacks.
	 */
	
	public static ArrayStack p1ArrayStack() {
		ArrayStack s = new ArrayStack();
		
		return s;
	}
	
	public static class ArrayStack {
		
		private static final int SIZE = 30;
		private List<Node> elements = new ArrayList<Node>(SIZE);
		private int[] top_curs = new int[] {-1, -1, -1};
		// one more list of freed nodes storing nodes which were
		// allocated but later released, and locate in front of
		// the last cursor of stack top; they should be allocated
		// first in the future
		
		public void push(int stackId, int i) {
			int top_cur = top_curs[stackId];
			Node lastNode = null;
			if (top_cur != -1)
				lastNode = elements.get(top_cur);
			
			top_curs[stackId] = getFreeIndex();
			Node n = new Node(i);
			n.next = lastNode;
			elements.add(top_curs[stackId], n);
		}
		
		private int getFreeIndex() {
			// try to retrieve index from the freed nodes list first
			
			// if there is no one available on freed nodes list
			return Math.max(Math.max(top_curs[0], top_curs[1]), top_curs[2]) + 1;
		}
		
		public Node pop(int stackId) {
			if (top_curs[stackId] == -1)
				return null;
			
			Node n = elements.get(top_curs[stackId]);
			top_curs[stackId] = elements.indexOf(n.next);
			// add one free node to the list of freed nodes
			return n;
		}
		
	}
	
	/* Stack and queue can be implemented with either array
	 * or linked-list. If the # of elements is relatively
	 * stable, array is better as it uses less space (no pointer);
	 * however, if the size always changes dramatically,
	 * linked-list can avoid consistently allocating large space.
	 */
	
	/**
	 * Simple stack implementation using linked-list.
	 */
	public static class Stack {
		
		private Node top;
		
		public void push(int i) {
			Node n = new Node(i);
			n.next = top;
			top = n;
		}
		
		public Node pop() {
			if (top == null)
				return null;
			
			Node n = top;
			top = top.next;
			return n;
		}
	}
	
	/**
	 * Simple queue implementation using linked-list.
	 */
	public static class Queue {
		
		private Node head;
		private Node tail;
		
		public void enqueue(int i) {
			if (head == null) {
				Node n = new Node(i);
				head = n;
				tail = n;
			} else {
				tail.next = new Node(i);
				tail = tail.next;
			}
		}
		
		public Node dequeue() {
			if (head == null)
				return null;

			// even if there is only one node in the queue,
			// do not need to update tail -- it will be updated
			// when enqueue is called
			Node n = head;
			head = head.next;
			return n;
		}
	}

}
