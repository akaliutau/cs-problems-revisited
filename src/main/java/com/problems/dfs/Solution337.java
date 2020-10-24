package com.problems.dfs;

import com.problems.model.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only
 * one entrance to this area, called the "root." Besides the root, each house
 * has one and only one parent house. After a tour, the smart thief realized
 * that "all houses in this place forms a binary tree". It will automatically
 * contact the police if two directly-linked houses were broken into on the same
 * night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without
 * alerting the police.
 * 
 * Algorithm
 * 
 * Use a helper function which receives a node as input and returns a
 * two-element array, where the first element represents the maximum amount of
 * money the thief can rob if starting from this node without robbing this node,
 * and the second element represents the maximum amount of money the thief can
 * rob if starting from this node and robbing this node.
 * 
 * The basic case of the helper function should be null node, and in this case,
 * it returns two zeros.
 * 
 * Finally, call the helper(root) in the main function, and return its maximum
 * value.
 * 
 */
public class Solution337 {

	int[] helper(TreeNode node) {
		// return [rob this node, not rob this node]
		if (node == null) {
			return new int[] { 0, 0 };
		}
		int left[] = helper(node.left);
		int right[] = helper(node.right);
		// if we rob this node, we cannot rob its children
		int rob = node.val + left[1] + right[1];
		// else, we free to choose rob its children or not
		int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

		return new int[] { rob, notRob };
	}

	public int rob(TreeNode root) {
		int[] answer = helper(root);
		return Math.max(answer[0], answer[1]);
	}

}
