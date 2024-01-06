package problem.bst;

import problem.model.TreeNode;

/**
 * Given a binary tree root, the task is to return the maximum sum of all keys
 * of any sub-tree which is also a Binary Search Tree (BST). 
 * 
 * IDEA: 
 * 
 * 1) traverse the BST using [lower, upper] boundary check on each node 
 * 
 * 2) collect the sum when traversing
 */
public class Solution1373 {

    int minVal = -100000;
    int maxVal = 100000;

    static class Result {
        int sum;
        int min;
        int max;
        boolean isBST;

        public Result(int min, int max, int sum, boolean isBST) {
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.isBST = isBST;
        }

        public Result(int sum, boolean isBST) {
            this.sum = sum;
            this.isBST = isBST;
        }

    }

    Result traverse(TreeNode node, int[] max) {
    	// edge case
        if (node == null)
            return new Result(maxVal, minVal, 0, true);

        // retrieve necessary info about underlying nodes
        Result left = traverse(node.left, max);
        Result right = traverse(node.right, max);

        if (left.isBST && right.isBST) {// left and right subtrees are bst
            // this block exec only if entire subtree is a bst
            if (node.val > left.max && node.val < right.min) {
                int sum = node.val + left.sum + right.sum;
                max[0] = Math.max(max[0], sum);
                return new Result(Math.min(node.val, left.min), Math.max(node.val, right.max), sum, true);
            }
        }

        return new Result(maxVal, minVal, 0, false);
    }

    public int maxSumBST(TreeNode root) {
        int[] sum = new int[1];
        traverse(root, sum);

        return sum[0];
    }

}
