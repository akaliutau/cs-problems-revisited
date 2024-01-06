package problem.tree;

import problem.model.TreeNode;

/**
 * 
 * Given the root of a binary tree, return the same tree where every subtree (of
 * the given tree) not containing a 1 has been removed.
 * 
 * A subtree of a node is node plus every node that is a descendant of
 * node.
 * 
 */
public class Solution814 {
	
	 boolean eligibleToPrune(TreeNode node) {
			if (node == null) {
				return false;
			}
			if (eligibleToPrune(node.left)) {
				node.left = null;
			}
			if (eligibleToPrune(node.right)) {
				node.right = null;
			}
			return (node.left == null && node.right == null && node.val == 0);
		}
		
		public TreeNode pruneTree(TreeNode root) {
	        if (root == null){
	            return null;
	        }
			eligibleToPrune(root);
			return root.left == null && root.right == null && root.val == 0 ? null : root;
		}
}
