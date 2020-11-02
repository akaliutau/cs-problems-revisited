package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given a binary tree root, the task is to return the maximum sum of all keys
 * of any sub-tree which is also a Binary Search Tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node contains only nodes with keys greater than
 * the node's key. Both the left and right subtrees must also be binary search
 * trees.
 * 
 * 
 * 
 */
public class Solution1373 {

	int[] compute(TreeNode root, int[] max) {
		if (root == null)
			return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };

		int[] left = compute(root.left, max);
		int[]  right = compute(root.right, max);
		
		if (root.val > left[1] && root.val < right[0]) {
			int sum = root.val + left[2] + right[2];
			max[0] = Math.max(max[0], sum);
			return new int[] { Math.min(root.val, left[0]), Math.max(root.val, right[1]), sum };
		}

		return new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 };
	}

	public int maxSumBST(TreeNode root) {
		int[] sum = new int[1];
		compute(root, sum);

		return sum[0];
	}

}
