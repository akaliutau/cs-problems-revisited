package com.problems.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import com.problems.model.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * IDEA:
 * 
 */
public class Solution99 {

	void swap(TreeNode a, TreeNode b) {
		int tmp = a.val;
		a.val = b.val;
		b.val = tmp;
	}

	public void recoverTree(TreeNode root) {
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode x = null, y = null, pred = null;

		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.add(root);
				root = root.left;
			}
			root = stack.removeLast();
			if (pred != null && root.val < pred.val) {
				y = root;
				if (x == null)
					x = pred;
				else
					break;
			}
			pred = root;
			root = root.right;
		}

		swap(x, y);
	}


}
