package com.problems.dfs;

import java.util.Arrays;

/**
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there
 * is an edge between nodes ai and bi, and two nodes source and destination of
 * this graph, determine whether or not all paths starting from source
 * eventually, end at destination, that is:
 * 
 * At least one path exists from the source node to the destination node 
 * 
 * If a path exists from the source node to a node with no outgoing edges, then that
 * node is equal to destination. The number of possible paths from source to
 * destination is a finite number. Return true
 * 
 * IDEA:
 * 1) create a graph of interlinked nodes
 * 2) invoke dfs on this graph
 * 
 */
public class Solution1059 {

	int n = 10010;
	int[] destMap = new int[n], oneway = new int[n];
	int[] idSrc = new int[n];// id of all source nodes
	int idx = 0;

	void add(int src, int dest) {// forming linked list of nodes, f.e. [0,1], [1,2]
		destMap[idx] = dest;      // destMap[0] = 1
		oneway[idx] = idSrc[src];// oneway[0] = -1 - always = -1, if only 1 path exists
		idSrc[src] = idx;    // idSrc[0] = 0
		idx++;
	}

	boolean[] seen;

	boolean dfs(int node, int dest) {
		if (node == dest) {
			return true;
		}
		if (idSrc[node] == -1 || seen[node]) {
			return false;
		}
		seen[node] = true;
		int id = idSrc[node];
		while (id != -1) {// go through all possibilities
			int j = destMap[id];
			if (!dfs(j, dest)) {
				return false;
			}
			id = oneway[id];
		}
		seen[node] = false;// backtracking
		return true;
	}

	public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
		Arrays.fill(idSrc, -1);
		seen = new boolean[n];
		// create a graph
		for (int[] e : edges) {
			int src = e[0], dest = e[1];
			if (src == destination) {
				return false;
			}
			add(src, dest);
		}
		
		return dfs(source, destination);
	}

}
