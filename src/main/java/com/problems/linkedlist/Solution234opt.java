package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Example 1:
 * 
 * Input: 1->2 Output: false
 * 
 * IDEA:
 * split into simple subproblems
 * in particular,
 * 1) reverse op can be applied on any range of LL
 * 2) reverse op is reversable (Group op)
 * 
 */
public class Solution234opt {

	ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode nextTemp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = nextTemp;
		}
		return prev;
	}

	ListNode findMid(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public boolean isPalindrome(ListNode head) {

		if (head == null)
			return true;

		// Find the end of first half and reverse second half.
		ListNode firstHalfEnd = findMid(head);
		ListNode secondHalfStart = reverseList(firstHalfEnd.next);

		// Check whether or not there is a palindrome.
		ListNode p1 = head;
		ListNode p2 = secondHalfStart;
		boolean result = true;
		while (result && p2 != null) {
			if (p1.val != p2.val)
				result = false;
			p1 = p1.next;
			p2 = p2.next;
		}

		// Restore the list and return the result.
		firstHalfEnd.next = reverseList(secondHalfStart);
		return result;
	}

}
