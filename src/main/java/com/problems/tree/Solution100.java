package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given two binary trees, write a function to check if they are the same or
 * not. Two binary trees are considered the same if they are structurally
 * identical and the nodes have the same value. 
 * 
 *  Example 1: Input: 
 *  
 *     1          1 
 *    / \        / \ 
 *   2   3      2   3 
 *   
 *   [1,2,3], [1,2,3] Output: true
 *   
 *   IDEA: traverse both in the same order (does not matter, in which one)
 */
public class Solution100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            if (!isSameTree(p.left, q.left)) {
                return false;
            }
            if (!isSameTree(p.right, q.right)) {
                return false;
            }
            return true;
        }
        return false;

    }

}
