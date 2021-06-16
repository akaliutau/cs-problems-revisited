package problem.tree;

import problem.model.TreeNode;

/**
 * Given the root of a binary search tree and a target value, return the value
 * in the BST that is closest to the target.
 * 
 * IDEA:
 * 
 * 
 *
 */
public class Solution270 {
	
	public int closestValue(TreeNode root, double target) {
		int val = 0;
		int closest = root.val;
		while (root != null) {
			val = root.val;
			closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
			root = target < root.val ? root.left : root.right;
		}
		return closest;
	}

}
