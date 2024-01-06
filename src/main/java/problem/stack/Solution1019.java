package problem.stack;

import java.util.Stack;

import problem.model.ListNode;

/**
 * 
 * We are given a linked list with head as the first node. Let's number the
 * nodes in the list: node_1, node_2, node_3, ... etc.
 * 
 * Each node may have a next larger value: for node_i, next_larger(node_i) is
 * the node_j.val such that j > i, node_j.val > node_i.val, and j is the
 * smallest possible choice. If such a j does not exist, the next larger value
 * is 0.
 * 
 * Return an array of integers answer, where answer[i] =
 * next_larger(node_{i+1}).
 * 
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5]
 * represent the serialization of a linked list with a head node value of 2,
 * second node value of 1, and third node value of 5.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [2,1,5] Output: [5,5,0]
 * 
 * IDEA:
 * 1) use stack to hold all iterated elements so far
 * 2) on each iteration: if current element is bigger than that on stack,
 *    associate current element as the next bigger one.
 *
 */
public class Solution1019 {
	
	public int[] nextLargerNodes(ListNode head) {
		int size = 0;
		ListNode node = head;
		while (node != null) {// calculate length
			size++;
			node = node.next;
		}
		
		Stack<int[]> stk = new Stack<>();
		int arr[] = new int[size];
		int index = 0;
		while (head != null) {// go through each node

			while (stk.size() > 0 && stk.peek()[1] < head.val)
				arr[stk.pop()[0]] = head.val;

			stk.push(new int[] {index, head.val});
			head = head.next;
			index++;
		}
		return arr;
	}
}
