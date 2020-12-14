package com.problems.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * A tree is an undirected graph in which any two vertices are connected by
 * exactly one path. In other words, any connected graph without simple cycles
 * is a tree. Given a tree of n nodes labelled from 0 to n - 1, and an array of
 * n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected
 * edge between the two nodes ai and bi in the tree, you can choose any node of
 * the tree as the root. When you select a node x as the root, the result tree
 * has height h. Among all possible rooted trees, those with minimum height
 * (i.e. min(h)) are called minimum height trees (MHTs). Return a list of all
 * MHTs' root labels. You can return the answer in any order. The height of a
 * rooted tree is the number of edges on the longest downward path between the
 * root and a leaf.
 * 
 * IDEA:
 * 
 * The idea is that we trim out the leaf nodes layer by layer, until we reach the core of the graph, which are the centroids nodes.
 * 
 * Once we trim out the first layer of the leaf nodes (nodes that have only one connection), some of the non-leaf nodes would become leaf nodes.
 *
 * The trimming process continues until there are only two nodes left in the graph, which are the centroids that we are looking for.
 * 
 * 
 */
public class Solution310 {
    
    static class Node {
        final int id;
        final Set<Node> ref = new HashSet<>();
        
        public Node(int id) {
            this.id = id;
        }
        
        public boolean isLeaf() {
            return ref.size() == 1;
        }
    }

     public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // base cases
        if (n <= 2) {
            List<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                centroids.add(i);
            }
            return centroids;
        }
        
        // build the tree with bi-directional ties
        Node[] nodes= new Node[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
            set.add(i);
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            nodes[edge[0]].ref.add(nodes[edge[1]]);
            nodes[edge[1]].ref.add(nodes[edge[0]]);
        }

        // Initialize the first layer of leaves
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].isLeaf()) {
                q.add(nodes[i]);
            }
        }

        // Trim the leaves until reaching the centroids
         while (!q.isEmpty() && set.size() > 2) {
            int layer = q.size();
            // remove the current leaves along with the edges
            for (int i = 0; i < layer; i++) {
                Node leaf = q.poll();
                Node parent = leaf.ref.iterator().next();// leaf has only one ref - to its parent
                parent.ref.remove(leaf);// remove leaf ref
                set.remove(leaf.id);
                if (parent.isLeaf()) {
                    q.add(parent);
                }
            }
        }
       // The remaining nodes are the centroids of the graph
        return new ArrayList<>(set);
    }
}
