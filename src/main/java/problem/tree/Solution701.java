package problem.tree;

import problem.model.TreeNode;

/**
 *  Insert value into BST
 *  
 *  IDEA:
 *  1) find the first elem > val
 *           2
 *   	   /   \
 *  	(1)	     3
 *  
 *  In BST there is no need to restructure tree, only attach additional nodes
 *  Note, that the process is absolutely stable - at some point int time the node
 *  with node.left == null OR node.right == null will be found, => end of process
 */
public class Solution701 {

    static void insert(TreeNode node, int val) {
    	// filter out edge case - if parent node is the leaf
        if (node == null) {
            return;
        }
        
        if (node.val > val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                insert(node.left, val);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                insert(node.right, val);
            }
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        insert(root, val);
        return root;
    }

  

}
