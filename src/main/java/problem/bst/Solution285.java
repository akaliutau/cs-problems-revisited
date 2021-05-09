package problem.bst;

import java.util.ArrayDeque;

import problem.model.TreeNode;
import problem.model.TreeNode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of
 * that node in the BST. The successor of a node p is the node with the smallest
 * key greater than p.val Input: root = [2,1,3], p = 1 Output: 2 Explanation:
 * 1's in-order successor node is 2. Note that both p and the return value is of
 * TreeNode type.
 */
public class Solution285 {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // the successor is somewhere lower in the right subtree
        if (p.right != null) {
            p = p.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // the successor is somewhere upper in the tree
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        int inorder = Integer.MIN_VALUE;

        // inorder traversal : left -> node -> right
        while (!stack.isEmpty() || root != null) {
            // 1. go left till you can
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (inorder == p.val) {
                return root;
            }
            inorder = root.val;

            // 3. go one step right
            root = root.right;
        }

        // there is no successor
        return null;
    }

}
