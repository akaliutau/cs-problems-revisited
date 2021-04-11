package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * 
 * IDEA:
 * Traverse all nodes (does not matter in which order)
 * For each node must to have in local vars:
 *  1) current level of recursion
 *  2) result structure
 *  
 *  Process 2 cases for leaves:
 *  1) we have a better (== deeper) level - reset the result
 *  2) update the sum if the leaf is on the same level
 */
public class Solution1302 {

	static void sum(TreeNode node, int[] result, int level){
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null){
            if (result[0] < level){
                result[0] = level;
                result[1] = node.val;
            }else if (result[0] == level){
                result[1] += node.val;
            }
        }else{
            sum(node.left, result, level + 1);
            sum(node.right, result, level + 1);
        }
    }
    
    public int deepestLeavesSum(TreeNode root) {
        int[] result = new int[2];
        sum(root, result, 0);
        return result[1];
    }
    
	
}
