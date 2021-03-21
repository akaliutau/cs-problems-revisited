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
 * 0) use inorder traversing (simulated via Stack)
 * 1) start from the leftmost elem
 * 2) compare it with central
 * 3) in case of disorder - save to x | to y
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
			while (root != null) {// find the leftmost elem
				stack.add(root);  // add all elems that lead to this elem, i.e form a path
				root = root.left;
			}
			root = stack.removeLast();
			// this block should executed twice:
			// 1) find disorder - left_elem > right.elem
			// 2) find the next biggest elem to the found one
			if (pred != null && root.val < pred.val) {// omitted on the smallest elem in BST
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
