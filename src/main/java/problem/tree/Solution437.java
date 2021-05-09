package problem.tree;

import java.util.HashMap;
import java.util.Map;

import problem.model.TreeNode;

/**
 * 
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that tgt to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], tgt = 8
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
 * The paths that tgt to 8 are:
 * 
 * 1. 5 -> 3 
 * 2. 5 -> 2 -> 1 
 * 3. -3 -> 11
 * 
 * Symmetric case:
 *          10 
 *         / \ 
 *        5   5 
 *       / \   \ 
 *      1   2   2 
 *     / 
 *    3  
 *    
 * IDEA: 
 * 
 * prefix sum technique: one pass + linear time complexity
 * BF:
 * 10 + 5 + 3 + 3 = accSum
 * 10 + 5 + 3 + -2 = accSum
 * 10 + 5 + 2 + 1 = accSum
 * 
 * count also all valid partial sums that sums up to tgt:
 * 10    +   (-3 + 11) = accSum
 * partial     tgt       accSum  
 * partial = accSum - tgt
 * 
 * 1) use parameter accSum to dynamically calculate the partial sum
 * 2) use map accSum => counter
 * 2*) Note the symmetric use-case: line 93: remove the current tgt from the hashmap in order not to use it
 */
public class Solution437 {

	static class Result {
		int count = 0;
		int tgt;
	}

	public void preorder(TreeNode node, int accSum, Map<Integer, Integer> h, Result r, int tgt) {
		if (node == null)
			return;

		accSum += node.val;

		if (accSum == tgt) {// count the full path from the root node
			r.count++;
		}

		// number of times the (accSum − tgt) has occurred already
		r.count += h.getOrDefault(accSum - tgt, 0);
		
		// add the current tgt into hashmap to use it during the child nodes processing at line 70 on next iteration
		h.compute(accSum, (k,v) -> v == null ? 1 : v + 1);

		preorder(node.left, accSum, h, r, tgt);
		preorder(node.right, accSum, h, r, tgt);

		// remove the current tgt from the hashmap in order not to use it during
		// processing on other nodes in other branches
		h.compute(accSum, (k,v) -> v - 1);
	}

	public int pathSum(TreeNode root, int tgt) {
		Result r = new Result();
		Map<Integer, Integer> h = new HashMap<>();// must use map, because treenode values can be < 0
		preorder(root, 0, h, r, tgt);
		return r.count;
	}



}
