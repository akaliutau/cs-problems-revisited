package problem.tree;

import problem.model.TreeNode;

/**
 * 
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 * 
 * A height-balanced binary tree is a binary tree in which the depth of the two
 * subtrees of every node never differs by more than one.
 * 
 * IDEA:
 * 
 * build tree from the center
 * (use the feature of BST to represent a sorted array if traversing in-order)
 *
 */
public class Solution108 {
	void build(int[] nums, int left, int right, TreeNode node) {
		if (left > right) {
			return;
		}
		int mid = (left + right) / 2;
		node.val = nums[mid];
		TreeNode l = new TreeNode(-100000);
		build(nums, left, mid - 1, l);
		if (l.val != -100000) {
			node.left = l;
		}
		TreeNode r = new TreeNode(-100000);
		build(nums, mid + 1, right, r);
		if (r.val != -100000) {
			node.right = r;
		}
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		TreeNode node = new TreeNode(-100000);
		build(nums, 0, nums.length - 1, node);
		return node;
	}
}
