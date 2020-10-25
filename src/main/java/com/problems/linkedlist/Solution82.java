package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * Return the linked list sorted as well.
 * 
 * Example 1:
 * 
 * Input: 1->2->3->3->4->4->5 Output: 1->2->5
 * 
 */
public class Solution82 {

	ListNode remove(ListNode node) {
		if (node == null || node.next == null)
			return node;

		if (node.val == node.next.val)
			return remove(getNextNotEqualsToMe(node).next);

		node.next = remove(node.next);

		return node;
	}

	ListNode getNextNotEqualsToMe(ListNode node) {
		while (node.next != null && node.val == node.next.val)
			node = node.next;
		return node;
	}

	public ListNode deleteDuplicates(ListNode head) {
		return remove(head);
	}

}
