package problem.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import problem.model.TreeNode;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise
 * direction starting from root. Boundary includes left boundary, leaves, and
 * right boundary in order without duplicate nodes. (The values of the nodes may
 * still be duplicates.)
 * 
 * Left boundary is defined as the path from root to the left-most node. Right
 * boundary is defined as the path from root to the right-most node. If the root
 * doesn't have left subtree or right subtree, then the root itself is left
 * boundary or right boundary. Note this definition only applies to the input
 * binary tree, and not applies to any subtrees.
 * 
 * The left-most node is defined as a leaf node you could reach when you always
 * firstly travel to the left subtree if exists. If not, travel to the right
 * subtree. Repeat until you reach a leaf node.
 * 
 * The right-most node is also defined by the same way with left and right
 * exchanged.
 * 
 * Example
 * 
 * Input:  
 *     1 
 *      \ 
 *       2 
 *      / \ 
 *     3   4
 * 
 * Ouput: [1, 3, 4, 2]
 * 
 * Explanation: The root doesn't have left subtree, so the root itself is left
 * boundary. The leaves are node 3 and 4. The right boundary are node 1,2,4.
 * Note the anti-clockwise direction means you should output reversed right
 * boundary. So order them in anti-clockwise without duplicates and we have
 * [1,3,4,2].
 * 
 * IDEA:
 * 1. perform pre-order traversing of the tree to add all leaves
 * 2. add separately : left and right boundary (the right - using stack)
 * 
 */
public class Solution545 {
	
	public boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    void addLeaves(TreeNode root, List<Integer> collector) {
        if (isLeaf(root)) {
        	collector.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(root.left, collector);
            }
            if (root.right != null) {
                addLeaves(root.right, collector);
            }
        }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        // left boundary: go down till the first leaf
        TreeNode node = root.left;
        while (node != null) {
            if (!isLeaf(node)) {
                res.add(node.val);
            }
            if (node.left != null) {
            	node = node.left;
            } else {
            	node = node.right;
            }

        }
        addLeaves(root, res);
        Stack<Integer> s = new Stack<>();
        // right boundary: go down till the first leaf, 
        // but collect results in stack 
        // as we are going to reverse list

        node = root.right;
        while (node != null) {
            if (!isLeaf(node)) {
                s.push(node.val);
            }
            if (node.right != null) {
            	node = node.right;
            } else {
            	node = node.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

}
