package com.problems.tree;

import java.util.HashMap;
import java.util.Map;

import com.problems.model.TreeNode;

/**
 * 
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 *          10 
 *         / \ 
 *        5  -3 
 *       / \   \ 
 *      3   2   11 
 *     / \   \ 
 *    3  -2   1
 * 
 * Return 3. 
 * The paths that sum to 8 are:
 * 
 * 1. 5 -> 3 
 * 2. 5 -> 2 -> 1 
 * 3. -3 -> 11
 * 
 * Idea: prefix sum technique: one pass + linear time complexity
 */
public class Solution437 {

	static class Result {
		int count = 0;
		int sum;
	}

	public void preorder(TreeNode node, int currSum, Map<Integer, Integer> h, Result r) {
		if (node == null)
			return;

		currSum += node.val;

		if (currSum == r.sum)
			r.count++;

		// number of times the curr_sum − k has occurred already
		r.count += h.getOrDefault(currSum - r.sum, 0);

		// add the current sum into hashmap
		// to use it during the child nodes processing
		h.put(currSum, h.getOrDefault(currSum, 0) + 1);

		preorder(node.left, currSum, h, r);
		preorder(node.right, currSum, h, r);

		// remove the current sum from the hashmap
		// in order not to use it during
		// the parallel subtree processing
		h.put(currSum, h.get(currSum) - 1);
	}

	public int pathSum(TreeNode root, int sum) {
		Result r = new Result();
		r.sum = sum;
		Map<Integer, Integer> h = new HashMap<>();
		preorder(root, 0, h, r);
		return r.count;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
