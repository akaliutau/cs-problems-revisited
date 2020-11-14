package com.problems.tree;

import com.problems.model.TreeNode;

/**
 *
 */
public class Solution701 {

    static void insert(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        if (node.val > val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                insert(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                insert(node.right, val);
            }
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        insert(root, val);
        return root;
    }

  

}
