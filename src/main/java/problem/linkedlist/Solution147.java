package problem.linkedlist;

import problem.model.ListNode;

/**
 * Sort a linked list using insertion sort.
 */
public class Solution147 {

    public ListNode insertionSortList(ListNode lst) {
        ListNode head = new ListNode(-1);
        ListNode curr = lst;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            ListNode prev = head;

            // find the position to insert the current node
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            ListNode next = curr.next;
            // insert the current node to the new list
            curr.next = prev.next;
            prev.next = curr;

            // moving on to the next iteration
            curr = next;
        }

        return head.next;
    }

}
