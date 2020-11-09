package com.problems.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL. Initially, all next
 * pointers are set to NULL.
 */
public class Solution117 {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        // Initialize a queue data structure which contains
        // just the root of the tree
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        // Outer while loop which iterates over
        // each level
        while (q.size() > 0) {
            int size = q.size();
            // Iterate over all the nodes on the current level
            for (int i = 0; i < size; i++) {

                // Pop a node from the front of the queue
                Node node = q.poll();
                if (i < size - 1) {// connect all on the same level
                    node.next = q.peek();
                }

                // Add the children, if any
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
        return root;
    }

  

}
