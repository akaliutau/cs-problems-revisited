package com.problems.tree;

import java.util.HashMap;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, write a function to get the maximum width of the given
 * tree. The maximum width of a tree is the maximum width among all levels. The
 * width of one level is defined as the length between the end-nodes (the
 * leftmost and right most non-null nodes in the level, where the null nodes
 * between the end-nodes are also counted into the length calculation. It is
 * guaranteed that the answer will in the range of 32-bit signed integer.
 * Example 1:  Input: 
 *        1           <-- depth = 0, col = 0
 *       / \ 
 *      3   2         <-- depth = 0
 *     / \   \ 
 *    5   3   9       <-- depth = 0
 *    
 *    Output: 4 Explanation: The maximum
 * width existing in the third level with the length 4 (5,3,null,9).
 * 
 * Edge case:
 * 
 *        1           <-- depth = 0, col = 0
 *       / \ 
 *      3   2         <-- depth = 0
 *       \   \ 
 *        3   9       <-- depth = 0
 *       / \ / \
 *      4   67  9
 */
public class Solution662 {

    private Integer maxWidth = 0;
    private HashMap<Integer, Integer> firstColIndexTable;// mapping depth => index of col0

    void dfs(TreeNode node, Integer depth, Integer colIndex) {
        if (node == null)
            return;
        // initialize the value, for the first seen colIndex per level
        Integer firstColIndex = firstColIndexTable.computeIfAbsent(depth, d -> colIndex);// needed because of edge case: left branch in left part = null 

        maxWidth = Math.max(this.maxWidth, colIndex - firstColIndex + 1);// if firstColIndex=0, for the 2nd tree answer will be incorrect

        // Preorder dfs 
        dfs(node.left, depth + 1, 2 * colIndex);
        dfs(node.right, depth + 1, 2 * colIndex + 1);
    }

    public int widthOfBinaryTree(TreeNode root) {
        // table contains the first col_index for each level
        this.firstColIndexTable = new HashMap<Integer, Integer>();

        // start from depth = 0, and colIndex = 0
        dfs(root, 0, 0);

        return this.maxWidth;
    }

}
