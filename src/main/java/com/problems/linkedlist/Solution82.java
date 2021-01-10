package com.problems.linkedlist;

import com.problems.model.ListNode;

/**
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * Return the linked list sorted as well.
 * 
 * Example 1:
 * 
 * Input: 1->2->3->3->4->4->5 Output: 1->2->5
 * 
 * IDEA:
 * 
 * 1->2->3->3->4->4->5
 *       |  |
 *      2nd case 
 */
public class Solution82 {

	public ListNode deleteDuplicates(ListNode head) {
        ListNode sPointer = new ListNode(-1);
        ListNode first = sPointer;
        int lastCount = 1;
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null){
            if (prev != null){
                if (cur.val != prev.val){
                    if (lastCount < 2){
                        sPointer.next = prev;
                        sPointer = sPointer.next;
                    }
                    prev = cur;
                    lastCount = 1;
                }else{
                    lastCount ++;
                }
            }else{
                prev = cur;
            }
            cur = cur.next;
        }
        if (lastCount == 1 && prev != null){// add the last
            sPointer.next = prev;
        }else{// the tail consists from duplicates
            sPointer.next = null;
        }
        return first.next;
    }

}
