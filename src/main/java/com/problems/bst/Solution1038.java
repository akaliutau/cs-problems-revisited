package com.problems.bst;

import com.problems.model.TreeNode;

/**
 *
 */
public class Solution1038 {

    int sum = 0;

    public TreeNode bstToGst(TreeNode node) {
        if (node == null) {
            return node;
        }
        
        bstToGst(node.right);// traverse tree from the right to the left
        node.val = sum + node.val;
        sum = node.val;
        bstToGst(node.left);
        
        return node;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
