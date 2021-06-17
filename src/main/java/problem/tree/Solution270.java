package problem.tree;

import problem.model.TreeNode;

/**
 * Given the root of a binary search tree and a target value, return the value
 * in the BST that is closest to the target.
 * 
 * IDEA:
 * 
 * 1. start from root
 * 2. on each node choose the closest branch (as in classic binary search)
 * 3. update the closest val on each iteration
 * 
 *           4
 *         /   \
 *        2      7
 *      /  \
 *     0    3
 *           | 
 *           3.2
 *           
 *            
 */       
public class Solution270 {
	
	double dist(int val, double target) {
		return Math.abs(val - target);
	}
	
	public int closestValue(TreeNode root, double target) {
		int closest = root.val;
		while (root != null) {
			int val = root.val;
			if (dist(val, target) < dist(closest, target)) {// found the better candidate
				closest = val;
			}
			root = target < root.val ? root.left : root.right;
		}
		return closest;
	}

}
