package problem.tree;

import java.util.ArrayList;
import java.util.List;

import problem.model.TreeNode;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes'
 * values.
 * 
 * Preorder: 
 *  1) add current
 *  2) traverse left
 *  3) traverse right
 */
public class Solution144 {
    
    static void traverse(TreeNode node, List<Integer> lst) {
        if (node == null) {
            return;
        }
        lst.add(node.val);
        traverse(node.left, lst);
        traverse(node.right, lst);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        traverse(root, lst);
        return lst;

    }

}
