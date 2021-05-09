package problem.linkedlist;

import problem.model.ListNode;

/**
 * AKA ZigZag reorder Given a singly linked list L: L0 -> L1 -> ... -> Ln-1 ->
 * Ln, reorder it to: L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example 1:
 * 
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * 
 * Example 2:
 * 
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * 
 * IDEA:
 * 
 * split into simple subproblems
 * 
 */
public class Solution143 {

	public void reorderList(ListNode head) {
		if (head == null)
			return;

		// find the middle of linked list [Problem 876]
		// in 1->2->3->4->5->6 find 4
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// when fast goes out of range, slow will be @middle of the list

		// reverse the second part of the list [Problem 206]
		// convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
		// reverse the second half in-place
		ListNode prev = null, curr = slow, tmp;
		//        4  ->  5  ->  6
		//prev   cur    tmp 
		
		while (curr != null) {
			tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
		}
		
		// now prev points to the head of reversed linked list

		// merge two sorted linked lists [Problem 21]
		// merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
		ListNode first = head, second = prev;
		while (second.next != null) {
			tmp = first.next;
			first.next = second;
			first = tmp;

			tmp = second.next;
			second.next = first;
			second = tmp;
		}
	}



}
