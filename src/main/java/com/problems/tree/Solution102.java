package com.problems.tree;

import java.util.ArrayList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level). For example: Given binary tree
 * [3,9,20,null,null,15,7], 
 * 
 *      3 
 *     / \ 
 *    9  20 
 *       / \ 
 *      15  7 
 *      return its level order traversal
 * as: 
 * [ 
 *  [3], 
 *  [9,20], 
 *  [15,7] 
 *  ]
 */
public class Solution102 {

    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)// need to add level
            levels.add(new ArrayList<Integer>());

        // fill the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return levels;
        helper(root, 0);
        return levels;
    }

}
