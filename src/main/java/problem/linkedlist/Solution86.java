package problem.linkedlist;

import problem.model.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * Example:
 * 
 * Input: head = 1->4->3->2->5->2, x = 3 
 *       Output: 1->2->2->4->3->5 
 *  
 *  note   1 -> 2 -> 2  [<-- before 3        after 3 -->]  4 -> 3 -> 5
 *                                                       order is preserved
 * 
 * IDEA:
 * use extra 2 LL to grow both lists
 */
public class Solution86 {

	public ListNode partition(ListNode head, int x) {

		// before and after are the two pointers used to create the two list
		// bHead and aHead are used to save the heads of the two lists.
		// All of these are initialized with the dummy nodes created.
		ListNode bHead = new ListNode(0);
		ListNode before = bHead;
		ListNode aHead = new ListNode(0);
		ListNode after = aHead;

		while (head != null) {

			// If the original list node < x add it to the before list.
			if (head.val < x) {
				before.next = head;
				before = before.next;
			} else {
				// If the original list node is >= x  assign it to the after list.
				after.next = head;
				after = after.next;
			}

			// move ahead in the original list
			head = head.next;
		}

		// Last node of "after" list would also be ending node of the reformed list
		after.next = null;
		before.next = aHead.next;

		return bHead.next;
	}

}
