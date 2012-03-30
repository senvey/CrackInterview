package edu.uci.practices.datastructure;

/**
 * Serves as both single node and list header.
 * 
 * @author William
 */
public class Node {
	Node next = null;
	int data;

	@SuppressWarnings("unused")
	private Node() {
	}

	public Node(int d) {
		data = d;
	}

	public void appendData(int d) {
		Node n = new Node(d);
		getTail().next = n;
	}

	public Node getTail() {
		Node tail = this;
		while (tail.next != null)
			tail = tail.next;
		return tail;
	}

}