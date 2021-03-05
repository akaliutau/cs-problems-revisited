package com.problems.bst;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's
 * key. The right subtree of a node contains only nodes with keys greater than
 * the node's key. Both the left and right subtrees must also be binary search
 * trees.
 * 
 * 
 * Example 1:
 * 
 *    2 
 *   / \ 
 *  1   3
 * 
 * Input: [2,1,3] Output: true
 * 
 * IDEA:
 * for each node 3 conditions must be verified:
 * 1) central node in [minValue, maxValue]
 * 2) left node in [minValue, maxValue=central node]
 * 3) right node in [minValue==central node, maxValue]
 * 
 * In the beginning the range is [-inf, +inf], but then narrows
 * 
 */
public class Solution98 {
	
	boolean validate(TreeNode node, long minValue, long maxValue) {
	    if (node == null) {
	    	return true;
	    }

	    int val = node.val;
	    if (val <= minValue || val >= maxValue) {
	    	return false;
	    }

	    if (!validate(node.right, val, maxValue)) {
	    	return false;
	    }
	    if (!validate(node.left, minValue, val)) {
	    	return false;
	    }
	    return true;
	  }

	  public boolean isValidBST(TreeNode root) {
	    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
	  }
	  

	

}
