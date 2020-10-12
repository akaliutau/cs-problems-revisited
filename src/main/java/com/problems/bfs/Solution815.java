package com.problems.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * BFS
 */
public class Solution815 {

    static class Point {

        int node, depth;

        public Point(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * Detects intersection of 2 sorted routes
     * @param a - route 1
     * @param b - route 2
     * @return
     */
    boolean intersect(int[] route1, int[] route2) {
        int i = 0, j = 0;
        while (i < route1.length && j < route2.length) {
            if (route1[i] == route2[j]) {
                return true;
            }
            if (route1[i] < route2[j]) {
                i++;
            }else {
                j++;
            }
        }
        return false;
    }

    public int numBusesToDestination(int[][] routes, int s, int t) {
        if (s == t) {
            return 0;
        }
        int n = routes.length;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<>());
        }
        Set<Integer> seen = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Point> queue = new ArrayDeque<>();

        // Build the graph. Two buses are connected if
        // they share at least one bus stop.
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (int i = 0; i < n; ++i) {
            if (Arrays.binarySearch(routes[i], s) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], t) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.node, depth = info.depth;
            if (targets.contains(node)) {
                return depth + 1;
            }
            for (Integer nei : graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
