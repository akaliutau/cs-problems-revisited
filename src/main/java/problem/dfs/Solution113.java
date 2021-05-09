package problem.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import problem.model.TreeNode;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum. Note: A leaf is a node with no children. Example:
 * Given the below binary tree and sum = 22, 
 *          5 
 *         / \ 
 *        4   8 
 *       /   / \ 
 *     11   13  4 
 *    / \      / \ 
 *   7   2    5   1 
 *   
 *   Return: [ 
 *   [5,4,11,2], 
 *   [5,8,4,5] 
 *   ]
 *   
 *   IDEA:
 *   1) traverse a dynamic list with path and separate variable with partial sum
 *   
 */
public class Solution113 {

    static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    static void checkPath(TreeNode node, int partialSum, int sum, Stack<Integer> stack, List<List<Integer>> results) {
        if (node == null) {
            return;
        }

        int next = partialSum + node.val;
        stack.add(node.val);// add node to path

        if (isLeaf(node)) {
            if (next == sum) {
                results.add(new ArrayList<>(stack));// generate a result
            }
            stack.pop();// remove node from path - backtracking
            return;
        }

        checkPath(node.left, next, sum, stack, results);
        checkPath(node.right, next, sum, stack, results);

        stack.pop();// remove node from path - backtracking
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> array = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        checkPath(root, 0, sum, stack, array);
        return array;
    }

 

}
