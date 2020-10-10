package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * 
 * Tree
 * 
 * 2 Nodes are equal if:
 * 1) they both empty
 * 2) both have the same value
 * 3) the same is true for each underlying level 
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

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
