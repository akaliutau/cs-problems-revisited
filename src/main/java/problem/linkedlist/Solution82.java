package problem.linkedlist;

import problem.model.ListNode;

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
 * 1. move across the list the construct from pair [prev, cur] pointers
 * 2. stableSingleElem always points the the last unique elem
 * 
 * 
 * 
 * 1->2->3->3->4->4->5
 *     /    |
 *    |     head != head.next
 *    pred.next   
 */
public class Solution82 {

	public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // predecessor = the last node  before the sublist of duplicates
        ListNode pred = dummy;
        
        while (head != null) {
            // if it's a beginning of duplicates sublist then skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;    
                }
                // here head.next points either at null (end of list) or the different elem
                // skip all duplicates
                pred.next = head.next;     
            } else {
                pred = pred.next;// otherwise, move predecessor    
            }
            // advance main pointer
            head = head.next;    
        }  
        return dummy.next;
    }

}
