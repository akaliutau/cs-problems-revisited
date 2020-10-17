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
 */
public class Solution105 {

	static class Input {
		int preIndex = 0;
		int[] preorder;
		Map<Integer, Integer> indexMap = new HashMap<>();// map value -> its index
	}

	public TreeNode build(int inLeft, int inRight, Input input) {
		// if there is no elements to construct subtrees
		if (inLeft == inRight)
			return null;

		int rootVal = input.preorder[input.preIndex];
		TreeNode root = new TreeNode(rootVal);

		int index = input.indexMap.get(rootVal);

		input.preIndex++;
		root.left = build(inLeft, index, input);
		root.right = build(index + 1, inRight, input);
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

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
