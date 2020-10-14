package com.problems.tree;

import com.problems.model.TreeNode;

/**
 * Tree
 */
public class Solution333 {

    class Node {
        int min;
        int max;
        boolean bst;
        int size;

        public Node(int min, int max, int size, boolean bst) {
            this.min = min;
            this.max = max;
            this.size = size;
            this.bst = bst;
        }
    }

    public Node walk(TreeNode r) {
        if (r == null) {
            return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, true);
        }

        Node left = walk(r.left);
        Node right = walk(r.right);
        int minVal = Math.min(left.min, r.val);
        int maxVal = Math.max(right.max, r.val);

        // for each node: check range of left & right nodes + all subnodes must be BST
        if (!left.bst || !right.bst || left.max >= r.val || right.min <= r.val) {
            int countVal = Math.max(left.size, right.size);
            return new Node(minVal, maxVal, countVal, false);
        } else {
            int countVal = right.size + left.size + 1;
            return new Node(minVal, maxVal, countVal, true);
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        Node countNode = walk(root);

        return countNode.size;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
