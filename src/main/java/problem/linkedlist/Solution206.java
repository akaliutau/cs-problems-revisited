package problem.linkedlist;

import problem.model.ListNode;

/**
 * 
 * Reverse a singly linked list.
 * 
 * Example:
 * 
 * Input: 1->2->3->4->5->NULL Output: 5->4->3->2->1->NULL
 * 
 *      //        4  ->  5  ->  6
 *      //prev   cur   nextTemp
 *       
 *      //        4  ->  5  ->  6
 *      //       prev   cur   nextTemp 
 * 
 */
public class Solution206 {

	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode nextTemp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = nextTemp;
		}
		return prev;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
