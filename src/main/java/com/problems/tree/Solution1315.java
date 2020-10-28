package com.problems.tree;

import java.util.Stack;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, return the sum of values of nodes with even-valued
 * grandparent. (A grandparent of a node is the parent of its parent, if it
 * exists.) 
 * If there are no nodes with an even-valued grandparent, return 0
 */
public class Solution1315 {

    static class Pair {
        TreeNode node;// node
        boolean isParentEven; //the parent of node

        public Pair(TreeNode node, boolean isEven) {
            this.node = node;
            this.isParentEven = isEven;
        }
    }

    public int sumEvenGrandparent(TreeNode root) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, false));// with false because there are no grandparents
        int sum = 0;

        while (!stack.isEmpty()) {// use stack to simulate pre-order tree traversing
            Pair p = stack.pop();
            boolean isEven = p.node.val % 2 == 0 ? true : false;// note we do not update p.isEven

            if (p.node.left != null) {
                stack.push(new Pair(p.node.left, isEven));

                if (p.isParentEven) {// if parent of p.node has an even val, add
                    sum += p.node.left.val;
                }
            }

            if (p.node.right != null) {
                stack.push(new Pair(p.node.right, isEven));

                if (p.isParentEven) {
                    sum += p.node.right.val;
                }
            }
        }

        return sum;
    }

}
