package com.problems.tree;

import java.util.ArrayList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth
 * smallest element in it. 
 * 
 * Example 1: Input: root = [3,1,4,null,2], k = 1 
 * 
 *    3 
 *   / \
 *  1   4 
 *   \ 
 *    2 
 *    
 *    Output: 1
 */
public class Solution230 {

    List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
      }

      public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums = inorder(root, new ArrayList<>());
        return nums.get(k - 1);
      }

}
