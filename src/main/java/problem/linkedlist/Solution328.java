package problem.linkedlist;

import problem.model.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even
 * nodes. Please note here we are talking about the node number and not the
 * value in the nodes. You should try to do it in place. The program should run
 * in O(1) space complexity and O(nodes) time complexity. 
 * 
 * Example 1: Input:
 * 1->2->3->4->5->NULL Output: 1->3->5->2->4->NULL 
 * 
 * Example 2: Input:
 * 2->1->3->5->6->4->7->NULL Output: 2->3->6->7->1->5->4->NULL 
 * 
 * IDEA: The
 * algorithm traverses the original LinkedList and put the odd nodes into the
 * oddList and the even nodes into the evenList. To traverse a LinkedList we
 * need at least one pointer as an iterator for the current node. But here the
 * pointers odd and even not only serve as the tail pointers but also act as the
 * iterators of the original list.
 */
public class Solution328 {

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

}
