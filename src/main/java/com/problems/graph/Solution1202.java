package com.problems.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given a string s, and an array of pairs of indices in the string
 * pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any
 * number of times. Return the lexicographically smallest string that s can be
 * changed to after using the swaps. 
 * 
 * Example 1: Input: s = "dcab", pairs =
 * [[0,3],[1,2]] Output: "bacd" Explaination: Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * 
 * IDEA:
 * 1) build a network of connections
 * 2) for each node-letter: traverse through all tree and find the connected set
 * 3) sort and re-map all subsets
 * 
 */
public class Solution1202 {
    
    static class Node {
        boolean visited = false;
        int val;
        int pos;
        List<Node> next = new ArrayList<>();
        
        public Node(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
        
    }
    
      static void find(Node node, List<Node> visited) {
        for (Node n : node.next) {
            if  (n.visited) {
                continue;
            }
            visited.add(n);
            n.visited = true;
            find(n, visited);
        }
    }
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] str = s.toCharArray();
        int n = str.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(str[i], i);
        }
        for (List<Integer> pair : pairs) {
            nodes[pair.get(0)].next.add(nodes[pair.get(1)]);
            nodes[pair.get(1)].next.add(nodes[pair.get(0)]);
        }
        for (Node node : nodes) {
            if (node.visited) {
                continue;
            }
            List<Node> linked = new ArrayList<>();
            linked.add(node);
            node.visited = true;
            find(node, linked);
            List<Node> clone = new ArrayList<>(linked);
            Collections.sort(linked, (o,p) -> o.pos - p.pos);
            Collections.sort(clone, (o,p) -> o.val - p.val);
            int[] vals = new int[clone.size()];
            for (int i = 0; i < clone.size(); i++) {
                vals[i] =  clone.get(i).val;
            }
            for (int i = 0; i < clone.size(); i++) {
                linked.get(i).val =  vals[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append((char)(node.val));
        }       
        return sb.toString();
        
    }
}
