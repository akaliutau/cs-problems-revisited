package problem.dividenconquer;

import problem.model.ListNode;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * 
 * IDEA:
 * Use paired merge + dummy head technique
 * 1. divide all list on pairs, merge them, then form new pairs from merges, and so on
 */
public class Solution23 {

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode prev = new ListNode(0);
        ListNode toReturn = prev;
        
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

            ListNode temp = null;// new elem of merged list
            if (val1 < val2) {
                temp = new ListNode(val1);
                l1 = l1.next;
            } else {
                temp = new ListNode(val2);
                l2 = l2.next;
            }

            prev.next = temp;
            prev = prev.next;// make newly added node the last one
        }
        
        return toReturn.next;
    }

    /**
     * returns the result of merging lists on [start, end]
     * @param lists
     * @param start
     * @param end
     * @return
     */
    public ListNode mergeSublist(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];// if nothing to merge, return list itself
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

 

}
