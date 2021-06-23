package problem.unionfind;

/**
 * In this problem, a tree is an undirected graph that is connected and has no
 * cycles.
 * 
 * You are given a graph that started as a tree with n nodes labeled from 1 to
 * n, with one additional edge added. The added edge has two different vertices
 * chosen from 1 to n, and was not an edge that already existed. The graph is
 * represented as an array edges of length n where edges[i] = [ai, bi] indicates
 * that there is an edge between nodes ai and bi in the graph.
 * 
 * Return an edge that can be removed so that the resulting graph is a tree of n
 * nodes. If there are multiple answers, return the answer that occurs last in
 * the input.
 * 
 * IDEA:
 * add nodes one by one 
 * 
 *  1 -- 2
 *  |    |
 *  3 -- 4 -- 5
 *  
 *  linking nodes:
 *  
 *  1 --> 2    <-- union returns true, as nodes are separate
 *  3 --> 4    <-- union returns true
 *  1 --> 3    <-- union returns true, as already build components 1-2 and 3-4 are separate 
 *  4 --> 5    <-- union returns true, as main cluster and node #5 are separate 
 *  2 --> 4    <-- union returns false, as nodes 2 and 4 are from the same set
 *  
 *  Note, the order of testing of nodes is not important here
 *   
 */
public class Solution684 {

	static class Graph {
		int[] parent;
		int[] rank;

		public Graph(int size) {
			parent = new int[size];
			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
			rank = new int[size];
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);// repeat graph traversing until parent of parent found
			}
			return parent[x];
		}

		public boolean union(int x, int y) {
			int xr = find(x);
			int yr = find(y);
			if (xr == yr) {// x & y are in the same set
				return false;
			}
			// union x & y into one superset
			// the point of ranking is to 
			if (rank[xr] < rank[yr]) {// wins the node with higher rank
				parent[xr] = yr;
			} else if (rank[xr] > rank[yr]) {
				parent[yr] = xr;
			} else {// choose random or systematically
				parent[yr] = xr;// parent of x is going to become a parent of y as well
				rank[xr]++;
			}
			return true;
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
		int n = edges.length;
		Graph dsu = new Graph(n + 1);
		
		for (int[] edge : edges) {
			if (!dsu.union(edge[0], edge[1])) {
				return edge;
			}
		}
		
		return null;
	}

}
