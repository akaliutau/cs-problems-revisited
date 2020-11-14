package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
 * A subtree of s is a tree consists of a node in s and all of this node's descendants. 
 * The tree s could also be considered as a subtree of itself.
 * 
 * 
 * 2 Nodes are equal if:
 * 1) they both empty
 * 2) both have the same value
 * 3) the same is true for each underlying level 
 * 
 * IDEA:
 *  consider each node in s as node and check subtrees on equality
 */
public class Solution572 {

	boolean equals(TreeNode l, TreeNode r) {
		
		if (l == null && r == null) {
			return true;
		}
		
		if (l == null || r == null) {
			return false;
		}
		// compare val and structure on each underlying level 
		return l.val == r.val && equals(l.left, r.left) && equals(l.right, r.right);
	}

	boolean traverse(TreeNode s, TreeNode t) {
		return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
	}

	// t is a smaller tree
	public boolean isSubtree(TreeNode s, TreeNode t) {
		return traverse(s, t);
	}


}
