package com.problems.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * Example:
 * 
 * Input: [1,2,3,null,5,null,4] Output: [1, 3, 4] 
 * Explanation:
 * 
 *         1        <--- bfs level 0
 *        / \ 
 *       2   3      <--- bfs level 1
 *        \   \ 
 *         5   4    <--- bfs level 2
 * 
 * IDEA:
 *  1) use BFS traversing type for tree (i.e. by layers)
 *  2) in this type of traversal the last element in layer obviously will be the rightmost 
 * 
 */
public class Solution199 {
	
	 public List<Integer> rightSideView(TreeNode root) {
	        if (root == null) return new ArrayList<>();
	        
	        Deque<TreeNode> queue = new ArrayDeque<>();
	        queue.add(root);
	        List<Integer> rightside = new ArrayList<>();
	        
	        while (!queue.isEmpty()) {
	            int levelLength = queue.size();

	            for(int i = 0; i < levelLength; ++i) {// process all what we have at the current moment
	                TreeNode node = queue.poll();
	                // if it's the rightmost element
	                if (i == levelLength - 1) {
	                    rightside.add(node.val);    
	                }

	                // add child nodes in the queue
	                if (node.left != null) {
	                    queue.add(node.left);    
	                }
	                if (node.right != null) {
	                    queue.add(node.right);
	                }
	            }
	        }
	        return rightside;
	    }

	

}
