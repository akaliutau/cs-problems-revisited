package com.problems.dfs;

import com.problems.model.ListNode;
import com.problems.model.TreeNode;

/**
 * Given the head of a singly linked list where elements are sorted in ascending
 * order, convert it to a height balanced BST.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * Input: head = [-10,-3,0,5,9] Output: [0,-3,9,-10,null,5] Explanation: One
 * possible answer is [0,-3,9,-10,null,5], which represents the shown height
 * balanced BST.
 * 
 * ➔ function formBst(start, end) 
 *   mid = (start + end) / 2 
 *   formBst(start, mid - 1) 
 *   TreeNode(head.val) 
 *   head = head.next 
 *   formBst(mid + 1, end)
 * 
 * 
 */
public class Solution109 {

	private ListNode head;

	int findSize(ListNode head) {
		ListNode ptr = head;
		int c = 0;
		while (ptr != null) {
			ptr = ptr.next;
			c += 1;
		}
		return c;
	}

	TreeNode formBst(int l, int r) {
		// Invalid case
		if (l > r) {
			return null;
		}

		int mid = (l + r) / 2;

		// First step of simulated inorder traversal. Recursively form
		// the left half
		TreeNode left = formBst(l, mid - 1);

		// Once left half is traversed, process the current node
		TreeNode node = new TreeNode(head.val);
		node.left = left;

		// Maintain the invariance mentioned in the algorithm
		head = head.next;

		// Recurse on the right hand side and form BST out of them
		node.right = formBst(mid + 1, r);
		return node;
	}

	public TreeNode sortedListToBST(ListNode head) {
		// Get the size of the linked list first
		int size = this.findSize(head);

		this.head = head;

		// Form the BST now that we know the size
		return formBst(0, size - 1);
	}

}
