package com.problems.dfs;

import com.problems.model.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number. An example is the root-to-leaf path 1->2->3 which
 * represents the number 123. Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children. 
 * 
 * Example: Input: [1,2,3] 
 * 
 *   1 
 *  / \ 
 * 2   3
 * 
 * Output: 25 
 * 
 * Explanation: The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13. Therefore, sum = 12 + 13
 * = 25.
 */
public class Solution129 {

    int rootToLeaf = 0;

    public void preorder(TreeNode r, int currNumber) {
        if (r != null) {
            currNumber = currNumber * 10 + r.val;
            // if it's a leaf, update root-to-leaf sum
            if (r.left == null && r.right == null) {
                rootToLeaf += currNumber;
            }
            preorder(r.left, currNumber);
            preorder(r.right, currNumber);
        }
    }

    public int sumNumbers(TreeNode root) {
        preorder(root, 0);
        return rootToLeaf;
    }
}
