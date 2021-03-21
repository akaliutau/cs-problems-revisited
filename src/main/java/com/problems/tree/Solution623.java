package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, then value v and depth d, you need to add a
 * row of nodes with value v at the given depth d. The root node is at depth 1.
 * 
 * The adding rule is: given a positive integer depth d, for each NOT null tree
 * nodes N in depth d-1, create two tree nodes with value v as N's left subtree
 * root and right subtree root. And N's original left subtree should be the left
 * subtree of the new left subtree root, its original right subtree should be
 * the right subtree of the new right subtree root. If depth d is 1 that means
 * there is no depth d-1 at all, then create a tree node with value v as the new
 * root of the whole original tree, and the original tree is the new root's left
 * subtree.
 * 
 * 
 * Example 1: Input:
 * 
 * A binary tree as following:   
 *     4 
 *    / \ 
 *   2   6
 *  / \ / 
 * 3  1 5
 * 
 * v = 1
 * 
 * d = 2
 * 
 * Output:   
 * 
 * 
 *       4 
 *      / \ 
 *     1   1
 *    /     \
 *   2       6
 *  / \     /
 * 3   1   5
 * 
 */
public class Solution623 {

	static void addNode(TreeNode parent, TreeNode child, int val, int branch) {
		TreeNode toInsert = new TreeNode(val);
		if (branch == -1) {
			parent.left = toInsert;
			toInsert.left = child;
		} else {
			parent.right = toInsert;
			toInsert.right = child;
		}
	}

	public void preorder(TreeNode parent, int v, int d, int level) {
		if (parent == null) {
			return;
		}
		if (level + 1 == d) {
			addNode(parent, parent.left, v, -1);
			addNode(parent, parent.right, v, +1);
			return;
		}
		preorder(parent.left, v, d, level + 1);
		preorder(parent.right, v, d, level + 1);
	}

	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode toInsert = new TreeNode(v);
			toInsert.left = root;
			return toInsert;
		}
		preorder(root, v, d, 1);
		return root;
	}

}
