package problem.tree;

import problem.model.TreeNode;

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
 * NOTE: we cannot transfer the same object across all branches, as sums in left/right are not correlate  
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
	
	double average = Integer.MIN_VALUE;

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


	public double maximumAverageSubtree(TreeNode root) {
		dfs(root);
		return average;
	}



}
