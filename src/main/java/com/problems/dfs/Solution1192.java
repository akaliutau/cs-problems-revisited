package com.problems.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [a, b]
 * represents a connection between servers a and b. Any server can reach any
 * other server directly or indirectly through the network.
 * 
 * A critical connection is a connection that, if removed, will make some server
 * unable to reach some other server.
 * 
 * Return all critical connections in the network in any order
 * 
 * 
 */
public class Solution1192 {

	private static void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int[] time, int prevNode, int curNode,
			int curTime, List<List<Integer>> results) {
		time[curNode] = curTime;
		visited[curNode] = true;
		for (int neighbor : graph.get(curNode)) {
			if (neighbor == prevNode) {
				continue;
			}
			if (!visited[neighbor]) {
				dfs(graph, visited, time, curNode, neighbor, curTime + 1, results);
			}
			time[curNode] = Math.min(time[curNode], time[neighbor]);
			if (time[neighbor] > curTime) {
				results.add(Arrays.asList(curNode, neighbor));
			}
		}
	}

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		List<List<Integer>> res = new LinkedList<>();
		for (List<Integer> connection : connections) {
			int cur = connection.get(0);
			int ref = connection.get(1);
			graph.putIfAbsent(cur, new ArrayList<>());
			graph.putIfAbsent(ref, new ArrayList<>());
			graph.get(cur).add(ref);
			graph.get(ref).add(cur);
		}
		int[] time = new int[n];
		for (int i = 0; i < n; ++i) {
			time[i] = i;
		}
		boolean[] visited = new boolean[n];
		int prevNode = -1;
		int curNode = 0;
		int curRank = 0;
		dfs(graph, visited, time, prevNode, curNode, curRank, res);
		return res;
	}

}