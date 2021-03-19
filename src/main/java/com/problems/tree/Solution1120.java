package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, find the maximum average value of any
 * subtree of that tree.
 * 
 * (A subtree of a tree is any node of that tree plus all its descendants. The
 * average value of a tree is the sum of its values, divided by the number of
 * nodes.)
 * 
 * IDEA:
 * just collect state during traversing
 * for each node calculate the avg using stats about childs + current node
 * 
 */
public class Solution1120 {

	static class Result {
		int sum;
		int count;

		public Result(int sum, int count) {
			this.sum = sum;
			this.count = count;
		}
	}

	Result dfs(TreeNode root) {
		if (root == null) {
			return new Result(0, 0);
		}

		Result l = dfs(root.left);
		Result r = dfs(root.right);

		// for each node calculate the avg using stats about childs + current node
		int sum = l.sum + r.sum + root.val;
		int count = l.count + r.count + 1;
		double ave = (double) sum / count;

		if (ave > average) {
			average = ave;
		}

		return new Result(sum, count);
	}

	double average = Integer.MIN_VALUE;

	public double maximumAverageSubtree(TreeNode root) {
		dfs(root);
		return average;
	}



}
