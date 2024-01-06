package problem.linkedlist;

import problem.model.ListNode;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head
 */
public class Solution19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;// remove the nth
        return dummy.next;
    }
    
	

}
