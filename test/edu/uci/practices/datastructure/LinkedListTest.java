package edu.uci.practices.datastructure;

import junit.framework.TestCase;

import org.junit.Test;

public class LinkedListTest extends TestCase {
	
	@Test	// 2.5
	public void testDupStart() {
		final int outside = 5;
		final int cycleSize = 10;
		
		Node head = new Node(1);
		for (int i = 1; i < outside; ++i)
			head.appendData(i);
		
		Node cycle = new Node(8);
		for (int i = 1; i < cycleSize; ++i)
			head.appendData(8 + i);
		
		head.getTail().next = cycle;
		cycle.getTail().next = cycle;
		
		assertEquals(cycle, LinkedList.p5DupStart(head));
	}
	
	@Test	// 2.4
	public void testAdd() {
		Node left = new Node(3);
		left.appendData(1);
		left.appendData(5);
		Node right = new Node(5);
		right.appendData(9);
		right.appendData(4);
		right.appendData(9);
		
		Node result = LinkedList.p4Add(left, right);
		printList(result);
	}
	
	@Test	// 2.3
	public void testRemoveNode() {
		Node head = new Node(10);
		for (int i = 9; i > 0; --i)
			head.appendData(i);
		
		Node middle = head;
		for (int i = 1; i < 7; ++i)
			middle = middle.next;
		int data = middle.data;
		
		LinkedList.p3RemoveNode(middle);
		printList(head);
		int length = 1;
		while (head.next != null) {
			assertNotSame(data, head.data);
			head = head.next;
			++length;
		}
		assertEquals(9, length);
	}
	
	@Test	// 2.2
	public void testNtoLast() {
		Node head = new Node(10);
		for (int i = 9; i > 0; --i)
			head.appendData(i);
		printList(head);
		
		assertNull(LinkedList.p2NtoLast(head, 11));
		assertNotNull(LinkedList.p2NtoLast(head, 10));
		assertEquals(3, LinkedList.p2NtoLast(head, 3).data);
	}

	@Test	// 2.1
	public void testRemoveDups() {
		Node head = new Node(1);
		head.appendData(2);
		head.appendData(3);
		head.appendData(1);
		head.appendData(3);
		head.appendData(4);
		head.appendData(1);

		printList(head);
		LinkedList.p1RemoveDups(head);
		printList(head);
	}

	private void printList(Node head) {
		while (head != null) {
			System.out.print(head.data + "\t");
			head = head.next;
		}
		System.out.println();
	}
}
