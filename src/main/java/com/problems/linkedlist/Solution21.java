package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 * Merge two sorted linked lists and return it as a new sorted list. 
 * The new list should be made by splicing together the nodes of the first two lists.
 * 
 */
public class Solution21 {
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // exactly one of l1 and l2 can be non-null at this point
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


	
	public static void main(String[] arg) {
		System.out.println(true);
	}

}
