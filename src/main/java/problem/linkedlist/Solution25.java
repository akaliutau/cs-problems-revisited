package problem.linkedlist;

import problem.model.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked
 * list. If the number of nodes is not a multiple of k then left-out nodes, in
 * the end, should remain as it is.
 * 
 * IDEA:
 * 
 * head ~ which will always point to the original head of the next set of k
 * nodes.
 * 
 * revHead ~ which is basically the tail node of the original set of k nodes.
 * Hence, this becomes the new head after reversal.
 * 
 * ktail ~ is the tail node of the previous set of k nodes after reversal.
 * 
 * newHead ~ acts as the head of the final list that we need to return as the
 * output. Basically, this is the kth node from the beginning of the original
 * list.
 * 
 * The core algorithm remains the same as before. Given the head, we first count
 * k nodes. If we are able to find at least k nodes, we reverse them and get our
 * revHead. 
 * 
 * At this point we check if we already have the variable ktail set or
 * not. It won't be set when we reverse the very first set of k nodes. However,
 * if this variable is set, then we attach ktail.next to the revHead.
 * 
 */
public class Solution25 {

	ListNode reverseLinkedList(ListNode head, int k) {
		// Reverse k nodes of the given linked list.
		// This function assumes that the list contains
		// at least k nodes.
		ListNode newHead = null;
		ListNode ptr = head;

		while (k > 0) {

			ListNode next = ptr.next;

			ptr.next = newHead;
			newHead = ptr;

			ptr = next;

			k--;
		}
		return newHead;
	}

	public ListNode reverseKGroup(ListNode head, int k) {

		ListNode ptr = head;
		ListNode ktail = null;

		// Head of the final linked list
		ListNode newHead = null;

		// Keep going until there are nodes in the list
		while (ptr != null) {

			int count = 0;

			// Start counting nodes from the head
			ptr = head;

			// Find the head of the next k nodes
			while (count < k && ptr != null) {
				ptr = ptr.next;
				count += 1;
			}

			// If we counted k nodes, reverse them
			if (count == k) {

				// Reverse k nodes and get the new head
				ListNode revHead = this.reverseLinkedList(head, k);

				if (newHead == null) {
					newHead = revHead;
				}

				// ktail is the tail of the previous block of reversed k nodes
				if (ktail != null) {
					ktail.next = revHead;
				}

				ktail = head;
				head = ptr;
			}
		}

		// attach the final, possibly un-reversed portion
		if (ktail != null)
			ktail.next = head;

		return newHead == null ? head : newHead;
	}

}
