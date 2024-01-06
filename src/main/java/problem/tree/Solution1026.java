package problem.tree;

import problem.model.TreeNode;

/**
 * 
 * Given the root of a binary tree, find the maximum value V for which there
 * exist different nodes A and B where V = |A.val - B.val| and A is an ancestor
 * of B.
 * 
 * A node A is an ancestor of B if either: any child of A is equal to B, or any
 * child of A is an ancestor of B
 * 
 * IDEA:
 * 1. Track the range [min, max] for values seen so far and pass this range to the next level
 * 2. use global accumulator to track the biggest difference
 * 
 */
public class Solution1026 {
	
    static void findDiff(TreeNode node, int[] r, int[] ans){
        if (node == null){
            return;
        } 
        int[] range = r.clone();
        range[0] = Math.min(range[0], node.val);
        range[1] = Math.max(range[1], node.val);
        ans[0] = Math.max(ans[0], range[1] - range[0]);
        findDiff(node.left, range, ans);
        findDiff(node.right, range, ans);
    }
    
    public int maxAncestorDiff(TreeNode root) {
        if (root == null){
            return 0;
        }
        int[] ans = new int[1];
        ans[0] = 0;
        int[] range = new int[]{root.val, root.val};
        findDiff(root, range, ans);
        return ans[0];
    }
}
