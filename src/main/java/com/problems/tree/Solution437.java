package com.problems.tree;

import java.util.HashMap;
import java.util.Map;

import com.problems.model.TreeNode;

/**
 * 
 * Tree
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
			r.count ++;

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
