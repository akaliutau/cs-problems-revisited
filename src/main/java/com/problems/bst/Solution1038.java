package com.problems.bst;

import com.problems.model.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
 * such that every key of the original BST is changed to the original key plus
 * sum of all keys greater than the original key in BST.
 * 
 * As a reminder, a binary search tree is a tree that satisfies these
 * constraints:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node contains only nodes with keys greater than
 * the node's key. Both the left and right subtrees must also be binary search
 * trees.
 * 
 *                    4             -30
 *                 /    \
 *                1      6          -21
 *               / \    / \
 *              0   2  5   7        -15
 *                  \       \
 *                   3        8     - 8
 *                   
 *           36  35  33 26
 * 
 *  IDEA:
 *  Traverse inorder-backward                 
 */
public class Solution1038 {

	int sum = 0;

	public TreeNode bstToGst(TreeNode node) {
		if (node == null) {
			return node;
		}

		bstToGst(node.right);// traverse tree from the right to the left
		node.val = sum + node.val;
		sum = node.val;
		bstToGst(node.left);

		return node;
	}



}
