package com.problems.tree;

import java.util.ArrayList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes'
 * values.
 */
public class Solution145 {

    static void traverse(TreeNode node, List<Integer> lst) {
        if (node == null) {
            return;
        }
        traverse(node.left, lst);
        traverse(node.right, lst);
        lst.add(node.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        traverse(root, lst);
        return lst;

    }

}
