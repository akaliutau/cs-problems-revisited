package problem.tree;

import problem.model.TreeNode;

/**
 * Check that the array represents preorder sequence of tree
 * 
 * IDEA:
 * transfer the index to the next node
 */
public class Solution1430 {

    boolean dfs(TreeNode root, int[] arr, int index) {
        if (index == arr.length - 1) {
            if (root == null || root.left != null || root.right != null) {
                return false;
            }
           return root.val == arr[index];
        }

        if (root != null && root.val != arr[index]) {
            return false;
        }
        if (root == null)
            return false;

        if (dfs(root.left, arr, index + 1)) {
            return true;
        }
        return dfs(root.right, arr, index + 1);
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);

    }

}
