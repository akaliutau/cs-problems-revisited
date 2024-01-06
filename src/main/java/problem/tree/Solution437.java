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
 *                                                                          
 *                                                                    runningSum
 *          10                                               1 <----   1
 *         / \                                             / 
 *        5  -3                                           1    <----   2          
 *       / \   \                                        /  \     
 *      3   2   11                           1 --->   -1    -1  <----   1
 *     / \   \                                       /        \
 *    3  -2   1  <--- sum = 18              2 --->  1          1  <----   2
 * 
 * Return 3.                                       tgt = 0
 * The paths that tgt to 8 are:                    paths: [1,-1],[-1,1],[1,-1],[-1,1]
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
 * BF: for each node calculate runningSum:
 * 
 * 10 + 5 + 3 + 3 = runningSum
 * 10 + 5 + 3 + -2 = runningSum
 * 10 + 5 + 2 + 1 = runningSum
 * 
 * And preserve these prefix sums in pathCount map.
 * On each node we have:
 * 
 * 1) count the full path from the root node, i.e. if runningSum == tgt
 * 2) count the partial paths from the root node, i.e. if (runningSum - tgt)
 * 
 * count also all valid partial sums that sums up to tgt:
 * 10    +   (-3 + 11) = runningSum
 * partial     tgt       runningSum  
 * partial = runningSum - tgt
 * 
 * 1) use parameter runningSum to dynamically calculate the partial sum
 * 2) use map runningSum => counter
 * 2*) Note the symmetric use-case: line 93: remove the current tgt from the hashmap in order not to use it
 */
public class Solution437 {

	static class Result {
		int count = 0;
	}

	public void preorder(TreeNode node, int runningSum, Map<Integer, Integer> pathCount, Result r, int tgt) {
		if (node == null)
			return;

		runningSum += node.val;

		if (runningSum == tgt) {// count the full path from the root node
			r.count++;
		}

		// number of times the (runningSum − tgt) has occurred already
		r.count += pathCount.getOrDefault(runningSum - tgt, 0);
		
		// add the current runningSum into map pathCount to use it during the child nodes processing at line 81 on next iteration
		// valid for all paths starting from node
		pathCount.compute(runningSum, (k,v) -> v == null ? 1 : v + 1);

		preorder(node.left, runningSum, pathCount, r, tgt);
		preorder(node.right, runningSum, pathCount, r, tgt);

		// remove the current runningSum from the map pathCount in order not to use it during
		// processing on other nodes in other branches
		pathCount.compute(runningSum, (k,v) -> v - 1);
	}

	public int pathSum(TreeNode root, int tgt) {
		Result r = new Result();
		// A map: prefix sum => how many times it has been seen so far
		Map<Integer, Integer> pathCount = new HashMap<>();// must use map, because treenode values can be < 0
		preorder(root, 0, pathCount, r, tgt);
		return r.count;
	}



}
