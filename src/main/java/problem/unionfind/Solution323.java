package problem.unionfind;

/**
 * 
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each
 * edge is a pair of nodes), write a function to find the number of connected
 * distinctSets in an undirected graph.
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
 * IDEA:
 * 
 *  building subsets:
 *  
 *  (0,1) : 0 <- 0, 1 <- 1           => 0 <- 1, set rank(0) = 1
 *  (1,2):  0 <- 1 <- 1,  2 <- 2     => 0 <- 1 and 0 <- 2
 *  
 *  in the beginning we have distinctSets consisting from n parts, 
 *  each merge will decrease this number by 1
 *  
 * 
 * 
 */
public class Solution323 {

	static class Graph {
		int[] rank;   // the same as height
		int[] parent; // contains the reference to the root node of component [i]
		int distinctSets;

		public Graph(int n) {
			rank = new int[n];
			parent = new int[n];
			distinctSets = n;
			for (int i = 0; i < n; i++) {
				parent[i] = i;// each node is a parent for itself
			}
		}

		public int find(int x) {
			if (parent[x] == x) {// found parent/root
				return x;
			}
			parent[x] = find(parent[x]);
			return parent[x];
		}

		public boolean union(int x, int y) {
			int px = find(x);
			int py = find(y);
			if (px == py) {// x & y belongs to the same set, drop this tie as it changes nothing
				return false;
			}
			// x & y are in the different set
			// px & py are their parents
			// to make tree balanced
			if (rank[px] > rank[py]) {// wins the parent with highest rank, i.e. px
				parent[py] = px;
			} else if (rank[py] > rank[px]) {
				parent[px] = py;
			} else {// if heights are equal, choose one  random
				parent[py] = px; // now node with ref py has a parent => route for y is longer 
				rank[px] ++;     // now the parent px has more advantages (provides a shorter path)
			}
			// we are merging 2 subsets into 1 set
			distinctSets--;
			return true;
		}

	}

	public int countComponents(int n, int[][] edges) {
		Graph g = new Graph(n);
		for (int[] edge : edges) {
			g.union(edge[0], edge[1]);
		}
		return g.distinctSets;
	}



}
