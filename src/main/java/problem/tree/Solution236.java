package problem.tree;

import problem.model.TreeNode;

/**
 * 
 * Tree
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given curNodes in the tree.
 * 
 * IDEA:
 * closest common ancestor is a fork point
 * 
 * there are 3 forks possible:
 * 1) both given nodes are in the same branch - the  LCA will be the highest node
 * 2) given nodes are in different branches - the  LCA will be the fork node
 */
public class Solution236 {

	TreeNode ans;

	boolean check(TreeNode curNode, TreeNode p, TreeNode q) {

		if (curNode == null) {
			return false;
		}

		boolean inLeftBranch = check(curNode.left, p, q);
		boolean inRigtBranch = check(curNode.right, p, q);

		boolean inCurNode = (curNode == p || curNode == q);

		if ((inLeftBranch && inRigtBranch) || (inLeftBranch && inCurNode) || (inRigtBranch && inCurNode)) {// this block is executed only un fork node
			this.ans = curNode;
		}

		return inLeftBranch || inRigtBranch || inCurNode;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		ans = null;
		check(root, p, q);
		return this.ans;
	}



}
