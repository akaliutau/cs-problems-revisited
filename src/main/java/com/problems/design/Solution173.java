package com.problems.design;

import java.util.Stack;

import com.problems.model.TreeNode;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be
 * initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * 
 * 
 * Example:
 *           9
 *         /  \
 *        7    15
 *       / \     \
 *      3   8     20
 *      
 * BSTIterator iterator = new BSTIterator(root); 
 * iterator.next(); // return 3
 * iterator.next(); // return 7 
 * iterator.hasNext(); // return true
 * iterator.next(); // return 9 
 * iterator.hasNext(); // return true
 * iterator.next(); // return 15 
 * iterator.hasNext(); // return true
 * iterator.next(); // return 20 
 * iterator.hasNext(); // return false
 * 
 * IDEA:
 * 1) collect on the stack the list of all leftmost nodes
 * 2) alg for each node:
 *    a) return the cur elem on the stack (which is always the leftmost)
 *    b) check the right branch - if exists, invoke leftmostInorder()
 * 
 */
public class Solution173 {
	
	class BSTIterator {

	    Stack<TreeNode> stack;

	    public BSTIterator(TreeNode root) {
	        
	        // Stack for the recursion simulation
	        this.stack = new Stack<TreeNode>();
	        
	        // Remember that the algorithm starts with a call to the helper function
	        // with the root node as the input
	        this.leftmostInorder(root);
	    }

	    private void leftmostInorder(TreeNode root) {
	      
	        // For a given node, add all the elements in the leftmost branch of the tree
	        // under it to the stack.
	        while (root != null) {
	            this.stack.push(root);
	            root = root.left;
	        }
	        // we have the leaf on the top of stack now
	    }

	    /**
	     * @return the next smallest number
	     */
	    public int next() {
	        // Node at the top of the stack is the next smallest element
	        TreeNode topmostNode = this.stack.pop();

	        // Need to maintain the invariant. 
	        // If the node has a right child, call the 
	        // helper function for the right child
	        if (topmostNode.right != null) {
	            this.leftmostInorder(topmostNode.right);
	        }

	        return topmostNode.val;
	    }

	    /**
	     * @return whether we have a next smallest number
	     */
	    public boolean hasNext() {
	        return this.stack.size() > 0;
	    }
	}


}
