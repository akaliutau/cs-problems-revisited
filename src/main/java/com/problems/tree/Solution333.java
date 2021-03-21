package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, find the largest subtree, which is also a
 * Binary Search Tree (BST), where the largest means subtree has the largest
 * number of nodes.
 * 
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the
 * below-mentioned properties:
 * 
 * The left subtree values are less than the value of their parent (root) node's
 * value. The right subtree values are greater than the value of their parent
 * (root) node's value. Note: A subtree must include all of its descendants.
 * 
 * IDEA:
 * traverse the tree in preorder way and collect full information about tree we have traversed just now
 * 
 */
public class Solution333 {

	/**
	 * 
	 * Descriptor for BST rooted at current node
	 * @author akaliutau
	 *
	 */
	class Node {
		int min;
		int max;
		boolean bst;
		int size;

		public Node(int min, int max, int size, boolean bst) {
			this.min = min;
			this.max = max;
			this.size = size;
			this.bst = bst;
		}
	}

	public Node dfs(TreeNode cur) {
		if (cur == null) {
			return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
		}

		Node left = dfs(cur.left);
		Node right = dfs(cur.right);
		// calculate the range
		int minVal = Math.min(left.min, cur.val);
		int maxVal = Math.max(right.max, cur.val);

		// for each node: check range of left & right nodes + all subnodes must be BST
		if (!left.bst || !right.bst || left.max >= cur.val || right.min <= cur.val) {
			int countVal = Math.max(left.size, right.size);// pick up the largest BST
			return new Node(minVal, maxVal, countVal, false);
		} else {//build a new BST if 1) all subnodes are BST and 2) ranges are correct
			int countVal = right.size + left.size + 1;
			return new Node(minVal, maxVal, countVal, true);
		}
	}

	public int largestBSTSubtree(TreeNode root) {
		Node countNode = dfs(root);

		return countNode.size;
	}



}
