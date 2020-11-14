package com.problems.tree;

import java.util.HashMap;
import java.util.Map;

import com.problems.model.TreeNode;

/**
 * 
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * For example, given
 * 
 * preorder = [3,9,20,15,7] inorder = [9,3,15,20,7] Return the following binary
 * tree:
 * 
 *        3 
 *       / \
 *      9   20 
 *          / \ 
 *         15  7
 *         
 *     0  1 23 4
 *         
 *  IDEA:
 *  1) use info about preorder to traverse tree
 *  2) use inorder info to terminate tree building
 *  3) use mapping nodeId/val => its linear center  
 *  
 *  
 */
public class Solution105 {

	static class Input {
		int preIndex = 0;
		int[] preorder;
		Map<Integer, Integer> indexMap = new HashMap<>();// map value -> its center
	}

	public TreeNode build(int inLeft, int inRight, Input input) {
		// if there is no elements to construct subtrees
		// because interval [i,i+1] between close nodes in projection cannot be divided
		if (inLeft == inRight)
			return null;

		int rootVal = input.preorder[input.preIndex];
		TreeNode root = new TreeNode(rootVal);

		int center = input.indexMap.get(rootVal);

		input.preIndex++;
		root.left = build(inLeft, center, input);// [0,1]
		root.right = build(center + 1, inRight, input); //[2,5]
		return root;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		Input input = new Input();
		input.preorder = preorder;

		int idx = 0;
		for (Integer val : inorder) {
			input.indexMap.put(val, idx++);
		}
		return build(0, inorder.length, input);
	}



}
