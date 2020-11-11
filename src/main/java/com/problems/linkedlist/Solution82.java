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
 * IDEA:
 * 
 * 1->2->3->3->4->4->5
 *       |  |
 *      2nd case 
 */
public class Solution82 {

    // return next non-equal or itself
    ListNode getNextNotEqualsToMe(ListNode node) {
        while (node.next != null && node.val == node.next.val) {
            node = node.next;
        }
        return node;
    }

	ListNode withoutDup(ListNode node) {
		if (node == null || node.next == null) {//edge case - withoutDups length = 0 | 1
			return node;
		}

		if (node.val == node.next.val) {
			return withoutDup(getNextNotEqualsToMe(node).next);// remove next to get a list which includes all unique numbers
		}

		node.next = withoutDup(node.next);

		return node;
	}

	public ListNode deleteDuplicates(ListNode head) {
		return withoutDup(head);
	}

}
