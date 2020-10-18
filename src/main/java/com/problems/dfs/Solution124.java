package com.problems.dfs;

import com.problems.model.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any node sequence from some starting
 * node to any node in the tree along the parent-child connections. The path
 * must contain at least one node and does not need to go through the root.
 * 
 * Input: root = [1,2,3] Output: 6
 * 
 */
public class Solution124 {

	int maxSum = Integer.MIN_VALUE;

	int traverse(TreeNode node) {
		if (node == null)
			return 0;

		// max sum on the left and right sub-trees of node
		int leftGain = Math.max(traverse(node.left), 0);
		int rightGain = Math.max(traverse(node.right), 0);

		// the price to start a new path if root is not included
		int withoutRoot = node.val + leftGain + rightGain;

		// update maxSum if it's better to start a new path
		maxSum = Math.max(maxSum, withoutRoot);

		return node.val + Math.max(leftGain, rightGain);
	}

	public int maxPathSum(TreeNode root) {
		traverse(root);
		return maxSum;
	}
}