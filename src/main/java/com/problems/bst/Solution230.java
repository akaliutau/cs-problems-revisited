package com.problems.bst;

import java.util.ArrayList;

import com.problems.model.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it. Example 1: Input: root = [3,1,4,null,2], k = 1 
 * 
 *      3 
 *    /   \
 *  1      4 
 *   \ 
 *    2
 *    
 *  0 1 2  3   
 *    
 * Output: 1
 */
public class Solution230 {

    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null)
            return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);// 0-indexed array
    }

}
