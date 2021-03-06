package com.problems.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus
 * repeats forever. For example if routes[0] = [1, 5, 7], this means that the
 * first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->...
 * forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Travelling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * Example: Input: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6 Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then
 * take the second bus to the bus stop 6.
 *
 * IDEA:
 * 
 * We perform a breadth first search on bus numbers. When we start at S,
 * originally we might be able to board many buses, and if we end at T we may
 * have many targets for our goal state.
 * 
 * 
 * 
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
	 * f.e. 
	 * [1, 2, 7], 
	 * [3, 6, 7]
	 */
	boolean intersect(int[] route1, int[] route2) {
		int i = 0, j = 0;
		while (i < route1.length && j < route2.length) {
			if (route1[i] == route2[j]) {
				return true;
			}
			if (route1[i] < route2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return false;
	}

	public int numBusesToDestination(int[][] routes, int src, int dest) {
		if (src == dest) {
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

		// Build the bi-directional graph. Two buses are connected if
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
			if (Arrays.binarySearch(routes[i], src) >= 0) {
				seen.add(i);
				queue.offer(new Point(i, 0));
			}
			if (Arrays.binarySearch(routes[i], dest) >= 0)
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



}
