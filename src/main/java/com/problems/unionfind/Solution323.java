package com.problems.unionfind;

/**
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * components in an undirected graph.
 * 
 * Example 1:
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * 
 *    0         3 
 *    |         | 
 *    1 --- 2   4
 * 
 * Output: 2
 * 
 */
public class Solution323 {

	static class Graph {
		int[] rank;
		int[] parent;
		int components;

		public Graph(int n) {
			rank = new int[n];
			parent = new int[n];
			components = 0;
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (parent[x] == x) {
				return x;
			}
			return parent[x] = find(parent[x]);
		}

		public boolean union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py) {
				return false;
			}
			// to make tree balanced
			if (rank[px] > rank[py]) {
				parent[py] = px;
			} else if (rank[py] > rank[px]) {
				parent[px] = py;
			} else {// to trigger rebalance
				parent[py] = px;
				rank[px] = rank[px] + 1;
			}
			components++;
			return true;
		}

	}

	public int countComponents(int n, int[][] edges) {
		Graph g = new Graph(n);
		for (int[] edge : edges) {
			g.union(edge[0], edge[1]);
		}
		return n - g.components;
	}



}
