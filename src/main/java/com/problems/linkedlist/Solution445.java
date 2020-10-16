package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * Idea: reverse lists:
 * 
 *         n1 -> n2 - > n3
 *               |   
 * last   head   tmp
 * (use shifted 3-node list)
 * 
 * add reversed lists
 * 
 * 
 */
public class Solution445 {

    public ListNode reverseList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // keep the next node
            ListNode tmp = head.next;
            // reverse the ref
            head.next = last;
            // update the last node and the current node
            last = head;
            head = tmp;
        }
        return last;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse lists
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode head = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // get the current values
            int x1 = l1 != null ? l1.val : 0;
            int x2 = l2 != null ? l2.val : 0;

            // current sum and carry
            int val = (carry + x1 + x2) % 10;
            carry = (carry + x1 + x2) / 10;

            // update the result: insert as the elem #0
            ListNode curr = new ListNode(val);
            curr.next = head;
            head = curr;

            // move to the next elements in the lists
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry != 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
