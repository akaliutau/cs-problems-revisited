package problem.tree;

import problem.model.TreeNode;

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
 * IDEA:
 * 1. combine check of BST with speculative sum collecting + record max recording
 * 2. on each level return the range of values for specific node
 *    for null nodes return a such structure that can satisfy BST condition, i.e. max level should be the Integer.MIN_VALUE to pass 
 *    left[1] < root.val for any val; sum obviously should be = 0 in this case
 */
public class Solution1373 {

	/**
	 * Returns {leftBranchValue, rightBranchValue, sum}
	 */
	int[] compute(TreeNode root, int[] sumAcc) {
		if (root == null)
			return new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE, 0 };// pass BST condition for any val

		int[] left =  compute(root.left, sumAcc);  // min value of all nodes in left = left[0], max = left[1]
		int[] right = compute(root.right, sumAcc); // max value of all nodes in right = right[1], min = right[0]
		
		if ( left[1] < root.val && root.val < right[0]) {
			int maxSum = root.val + left[2] + right[2];
			sumAcc[0] = Math.max(sumAcc[0], maxSum);
			return new int[] { Math.min(root.val, left[0]), Math.max(root.val, right[1]), maxSum };// forward the statistics to upper levels
		}

		return new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 0 };// not pass BST condition for any val
	}

	public int maxSumBST(TreeNode root) {
		int[] sum = new int[1];// used as an accumulator to hold the answer
		compute(root, sum);

		return sum[0];
	}

}
