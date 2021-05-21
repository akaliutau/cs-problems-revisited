package problem.linkedlist;

import problem.model.ListNode;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m
 * respectively.
 * 
 * Remove list1's nodes from the ath node to the bth node, and put list2 in
 * their place.
 * 
 * 
 * 
 */
public class Solution5558 {

	public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
		ListNode first = list1;
		while (a-- > 1) {
			first = first.next;
		}

		ListNode last = list1;
		while (b-- > 0) {
			last = last.next;
		}
		// compose
		first.next = list2;
		while (list2.next != null) {
			list2 = list2.next;
		}
		list2.next = last;
		return list1;
	}

}
