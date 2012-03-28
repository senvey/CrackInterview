package edu.uci.practices.datastructure;

/**
 * @author William
 * 
 *         NOTE: java.util.LinkedList&lt;E&gt; does not allow type E to be
 *         <i>int</i>.
 */
public class LinkedList {

	/**
	 * p2.5
	 * 
	 * Given a circular linked list, implement an algorithm which returns node
	 * at the beginning of the loop.
	 * 
	 * DEFINITION
	 * Circular linked list: A (corrupt) linked list in which a
	 * nodeÕs next pointer points to an earlier node, so as to make a loop in
	 * the linked list
	 * 
	 * EXAMPLE
	 * Input: A -> B -> C -> D -> E -> C [the same C as earlier] / Output: C
	 */
	
	// Tricky! -- using the algorithm on the book
	public static ListNode p5DupStart(ListNode circularList) {
		if (circularList == null)
			return null;
		
		ListNode slow = circularList;
		ListNode fast = circularList;
		
		// in case there is no cycle, return null in the end
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			// loop until ''fast'' catches ''slow''
			if (fast == slow) {
				slow = circularList;
				while (slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				return slow;
			}
		}
		
		return null;
	}

	/**
	 * p2.4
	 * 
	 * You have two numbers represented by a linked list, where each node
	 * contains a single digit. The digits are stored in reverse order, such
	 * that the 1Õs digit is at the head of the list. Write a function that adds
	 * the two numbers and returns the sum as a linked list.
	 * 
	 * EXAMPLE
	 * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2) / Output: 8 -> 0 -> 8
	 */
	
	public static ListNode p4Add(ListNode left, ListNode right) {
		if (left == null)
			return right;
		else
			if (right == null)
				return left;
		
		ListNode result = new ListNode(left.data + right.data);
		int addup = result.data / 10;
		result.data %= 10;
		
		while (left.next != null && right.next != null) {
			left = left.next;
			right = right.next;
			int temp = left.data + right.data + addup;
			addup = temp / 10;
			result.appendData(temp % 10);
		}
		if (left.next != null) {
			left = left.next;
			int temp = left.data + addup;
			addup = temp / 10;
			result.appendData(temp % 10);
		}
		if (right.next != null) {
			right = right.next;
			int temp = right.data + addup;
			addup = temp / 10;
			result.appendData(temp % 10);
		}
		if (addup != 0)
			result.appendData(addup);
		
		return result;
	}

	/**
	 * p2.3
	 * 
	 * Implement an algorithm to delete a node in the middle of a single linked
	 * list, given only access to that node.
	 * EXAMPLE
	 * Input: the node ÔcÕ from the linked list a->b->c->d->e
	 * Result: nothing is returned, but the new linked list looks like a->b->d->e
	 */

	public static void p3RemoveNode(ListNode middle) {
		// NOTE: cannot remove the last element!
		if (middle == null || middle.next == null)
			return;

		middle.data = middle.next.data;
		middle.next = middle.next.next;
	}

	/**
	 * p2.2
	 * 
	 * Implement an algorithm to find the nth to last element of a singly linked
	 * list.
	 */

	public static ListNode p2NtoLast(ListNode head, int n) {
		if (head == null)
			return null;

		ListNode begin = head;
		ListNode end = head;

		// move ''end'' to the position of n length to ''first'' (n-2 elements in between)
		for (int i = 1; i < n; ++i) {
			// move ''end'' first and check if it will be out of range next loop
			end = end.next;
			if (end == null)
				return null;
		}

		// move ''first'' and ''end'' together until ''end'' reaches the end
		while (end.next != null) {
			begin = begin.next;
			end = end.next;
		}

		return begin;
	}

	/**
	 * p2.1
	 * 
	 * Write code to remove duplicates from an unsorted linked list. FOLLOW UP:
	 * How would you solve this problem if a temporary buffer is not allowed?
	 */

	public static void p1RemoveDups(ListNode head) {
		if (head == null)
			return;

		ListNode cur = head;
		while (cur.next != null) {
			ListNode cycle = cur;
			while (cycle.next != null) {
				if (cur.data == cycle.next.data) {
					cycle.next = cycle.next.next;
					continue;
				} else {
					cycle = cycle.next;
				}
			}
			cur = cur.next;
		}
	}

}