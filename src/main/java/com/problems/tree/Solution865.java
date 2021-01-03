package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, the depth of each node is the shortest
 * distance to the root.
 * 
 * Return the smallest subtree such that it contains all the deepest nodes in
 * the original tree.
 * 
 * A node is called the deepest if it has the largest depth possible among any
 * node in the entire tree.
 * 
 * The subtree of a node is tree consisting of that node, plus the set of all
 * descendants of that node.
 * 
 * IDEA:
 * for each node choose the deepest branch and return it as an answer
 * 
 */
public class Solution865 {

	static class Result {
		TreeNode node;
		int dist;

		Result(TreeNode n, int d) {
			node = n;
			dist = d;
		}
	}

	// Return the result of the subtree at this node.
	Result dfs(TreeNode node) {
		if (node == null) {
			return new Result(null, 0);
		}
		Result l = dfs(node.left);
		Result r = dfs(node.right);
		if (l.dist > r.dist)
			return new Result(l.node, l.dist + 1);
		if (l.dist < r.dist)
			return new Result(r.node, r.dist + 1);
		return new Result(node, l.dist + 1);
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		return dfs(root).node;
	}

}
