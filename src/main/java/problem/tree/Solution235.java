package problem.tree;

import problem.model.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * IDEA:
 *             6
 *           /   \ 
 *         2       8
 *       /  \     /  \
 *      0    4   7    9 
 *          / \
 *         3   5
 *         
 * Having left and right values, traverse the BST with them:
 *          
 * In BST parent value is always a mid point
 * 
 * case 1:
 * both values are > mid point, then investigate the right branch
 * 
 * case 2:
 * both values are < mid point, then investigate the left branch
 * 
 * case 3:
 * else the values must be in different branches => this mid point is a LCA
 * 
 */
public class Solution235 {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode left, TreeNode right) {

		int leftValue = left.val;
		int rightValue = right.val;

		TreeNode node = root;

		while (node != null) {

			int midPoint = node.val;

			if (leftValue > midPoint && rightValue > midPoint) {
				node = node.right;
			} else if (leftValue < midPoint && rightValue < midPoint) {
				node = node.left;
			} else {
				// We have found the split point, i.e. the LCA node.
				return node;
			}
		}
		return null;
	}

}
