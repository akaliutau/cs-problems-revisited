package com.problems.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * 
 * Tree
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * For example: Given binary tree [3,9,20,null,null,15,7], 
 * 
 *       3 
 *      / \ 
 *     9  20 
 *    / \ 
 *   15  7
 * return its zigzag level order traversal as: [ [3], [20,9], [15,7] ]
 */
public class Solution103 {

	void dfs(TreeNode node, int level, List<List<Integer>> results) {
		if (level >= results.size()) {
			List<Integer> newLevel = new LinkedList<>();
			newLevel.add(node.val);
			results.add(newLevel);
		} else {// use different order of adding on even/odd levels
			if (level % 2 == 0) {
				results.get(level).add(node.val);
			}else {
				results.get(level).add(0, node.val);
			}
		}

		if (node.left != null) {
			dfs(node.left, level + 1, results);
		}
		if (node.right != null) {
			dfs(node.right, level + 1, results);
		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		List<List<Integer>> results = new ArrayList<>();
		dfs(root, 0, results);
		return results;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
