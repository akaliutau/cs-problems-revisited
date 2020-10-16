package com.problems.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Solution797 {

    private int target;
    private int[][] graph;
    private List<List<Integer>> results;

    protected void backtrack(int currNode, LinkedList<Integer> path) {
        if (currNode == target) {
            // Note: one should make a deep copy of the path
            results.add(new ArrayList<Integer>(path));
            return;
        }
        // explore the neighbor nodes one after another.
        for (int nextNode : graph[currNode]) {
            // mark the choice, before backtracking.
            path.addLast(nextNode);
            backtrack(nextNode, path);
            // remove the previous choice, to try the next choice
            path.removeLast();
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        target = graph.length - 1;
        this.graph = graph;
        results = new ArrayList<List<Integer>>();
        // adopt the LinkedList for fast access to the tail element.
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.addLast(0);
        // kick of the backtracking, starting from the source (node 0)
        backtrack(0, path);
        return results;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
