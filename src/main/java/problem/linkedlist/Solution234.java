package problem.linkedlist;

import java.util.Stack;

import problem.model.ListNode;

/**
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Example 1:
 * 
 * Input: 1->2 Output: false
 * 
 */
public class Solution234 {

	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode left = head;
		int len = 0;
		Stack<Integer> stack = new Stack<>();
		while (head != null) {
			stack.add(head.val);
			head = head.next;
			len++;
		}

		head = left;
		for (int i = 0; i < len / 2 + 1; i++) {
			if (head.val != stack.get(len - 1 - i)) {
				return false;
			}
			head = head.next;
		}
		return true;

	}

}
