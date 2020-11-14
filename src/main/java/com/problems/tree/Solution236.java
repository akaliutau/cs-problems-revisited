package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * 
 * Tree
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * IDEA:
 * closest common ancestor is a fork point
 */
public class Solution236 {

	TreeNode ans;

	boolean check(TreeNode node, TreeNode p, TreeNode q) {

		if (node == null) {
			return false;
		}

		boolean inLeftBranch = check(node.left, p, q);
		boolean inRigtBranch = check(node.right, p, q);

		boolean inCurNode = (node == p || node == q);

		if ((inLeftBranch && inRigtBranch) || (inLeftBranch && inCurNode) || (inRigtBranch && inCurNode)) {
			this.ans = node;
		}

		return inLeftBranch || inRigtBranch || inCurNode;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// Traverse the tree
		ans = null;
		check(root, p, q);
		return this.ans;
	}



}
