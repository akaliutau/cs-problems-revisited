package problem.bst;

import problem.model.TreeNode;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so
 * that the leftmost node in the tree is now the root of the tree, and every
 * node has no left child and only one right child. 
 * 
 * Example 1: 
 * 
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9] 
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 */
public class Solution897 {

    TreeNode inOrder(TreeNode root, TreeNode parent){
        if (root == null){
            return parent;
        }
        parent = inOrder(root.left, parent);
        parent.right = new TreeNode(root.val);
        parent = parent.right;
        parent = inOrder(root.right, parent);
        return parent;
   }
   
   public TreeNode increasingBST(TreeNode root) {
       TreeNode head = new TreeNode(-1);
       inOrder(root, head);
       return head.right;
   }
}
