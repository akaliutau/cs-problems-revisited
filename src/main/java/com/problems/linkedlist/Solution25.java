package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 *
 */
public class Solution25 {

    ListNode reverseLinkedList(ListNode head, int k) {

        // Reverse k nodes of the given linked list.
        // This function assumes that the list contains
        // at least k nodes.
        ListNode newHead = null;
        ListNode ptr = head;

        while (k > 0) {

            // Keep track of the next node to process in the
            // original list
            ListNode next = ptr.next;

            // Insert the node pointed to by "ptr"
            // at the beginning of the reversed list
            ptr.next = newHead;
            newHead = ptr;

            // Move on to the next node
            ptr = next;

            // Decrement the count of nodes to be reversed by 1
            k--;
        }

        // Return the head of the reversed list
        return newHead;

    }

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode ptr = head;
        ListNode ktail = null;

        // Head of the final linked list
        ListNode newHead = null;

        // Keep going until there are nodes in the list
        while (ptr != null) {

            int count = 0;

            // Start counting nodes from the head
            ptr = head;

            // Find the head of the next k nodes
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count += 1;
            }

            // If we counted k nodes, reverse them
            if (count == k) {

                // Reverse k nodes and get the new head
                ListNode revHead = this.reverseLinkedList(head, k);

                // newHead is the head of the final linked list
                if (newHead == null) {
                    newHead = revHead;
                }

                // ktail is the tail of the previous block of
                // reversed k nodes
                if (ktail != null) {
                    ktail.next = revHead;
                }

                ktail = head;
                head = ptr;
            }
        }

        // attach the final, possibly un-reversed portion
        if (ktail != null)
            ktail.next = head;

        return newHead == null ? head : newHead;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
