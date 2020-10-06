package com.problems.dividenconquer;

import com.problems.model.ListNode;

/**
 * Divide and conquer
 */
public class Solution23 {

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode prev = new ListNode(0);
        ListNode res = prev;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                prev.next = l2;
                break;
            } else if (l2 == null) {
                prev.next = l1;
                break;
            }

            int val1 = l1.val;
            int val2 = l2.val;

            ListNode temp = null;
            if (val1 < val2) {
                temp = new ListNode(val1);
                l1 = l1.next;
            } else {
                temp = new ListNode(val2);
                l2 = l2.next;
            }

            prev.next = temp;
            prev = prev.next;
        }
        return res.next;
    }

    public ListNode mergeSublist(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = (end + start) / 2;

        ListNode left = mergeSublist(lists, start, mid);
        ListNode right = mergeSublist(lists, mid + 1, end);

        return merge(left, right);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if (n == 0) {
            return null;
        }
        return mergeSublist(lists, 0, n - 1);
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
