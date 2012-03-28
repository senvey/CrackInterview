package edu.uci.practices.datastructure;

public class ListNode {
	ListNode next = null;
	int data;
	
	@SuppressWarnings("unused")
	private ListNode() {}
	public ListNode(int d) { data = d; }
	
	public void appendData(int d) {
		ListNode n = new ListNode(d);
		getTail().next = n;
	}
	
	public ListNode getTail() {
		ListNode tail = this;
		while (tail.next != null)
			tail = tail.next;
		return tail;
	}
	
}