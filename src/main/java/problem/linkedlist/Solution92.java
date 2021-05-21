package problem.linkedlist;

import problem.model.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * 
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * 
 * Example:
 * 
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4 Output: 1->4->3->2->5->NULL
 * 
 */
public class Solution92 {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		int len = n - m;
		if (len == 0) {
			return head;
		}
		ListNode left = head;
		ListNode prev = new ListNode(-1);
		prev.next = head;
		int counter = 0;
		while (++counter < m) {
			left = left.next;
			prev = prev.next;
		}
		ListNode next = left.next;
		ListNode first = left;

		// reverse code
		while (len-- > 0) {
			ListNode next2 = next.next;
			next.next = left;
			left = next;
			next = next2;
		}
        // include chain
		prev.next = left;
		first.next = next;

		return m == 1 ? prev.next : head;

	}

}
