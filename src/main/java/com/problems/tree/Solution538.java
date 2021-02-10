package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree
 * such that every key of the original BST is changed to the original key plus
 * sum of all keys greater than the original key in BST. As a reminder, a binary
 * search tree is a tree that satisfies these constraints: The left subtree of a
 * node contains only nodes with keys less than the node's key. The right
 * subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class Solution538 {

    static TreeNode convert(TreeNode root, int[] sum) {
        if (root != null) {
            convert(root.right, sum);
            sum[0] += root.val;
            root.val = sum[0];
            convert(root.left, sum);
        }
        return root;
    }

    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[1];
        return convert(root, sum);
    }

}
