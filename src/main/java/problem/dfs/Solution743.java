package problem.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are n network nodes, labelled 1 to n.
 * 
 * Given times, a list of travel times as directed edges times[i] = (u, v, w),
 * where u is the source node, v is the target node, and w is the time it takes
 * for a signal to travel from source to target.
 * 
 * now, we send a signal from a certain node k. How long will it take for all
 * nodes to receive the signal? If it is impossible, return -1.
 * 
 * Example 1
 * 
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2 Output: 2
 * 
 * IDEA:
 *  use dijkstra algorithm
 * 
 */
public class Solution743 {

	Map<Integer, Integer> dist;

	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for (int[] edge : times) {
			graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[] { edge[1], edge[2] });// note: only 2 elems in this array in comparison with times array
		}
		// Dijkstra alg
		dist = new HashMap<>();
		for (int node = 1; node <= n; ++node) {
			dist.put(node, Integer.MAX_VALUE);
		}

		// start from  node k
		dist.put(k, 0);
		boolean[] seen = new boolean[n + 1];

		while (true) {
			int cur = -1;
			// here a non-optimal approach is used, use a queue instead
			int fastest = Integer.MAX_VALUE;// we start from the biggest possible value
			for (int i = 1; i <= n; ++i) {// find the fastest non-processed yet node
				if (!seen[i] && dist.get(i) < fastest) {
					fastest = dist.get(i);
					cur = i;
				}
			}

			if (cur < 0) {
				break;
			}
			seen[cur] = true;// process this node
			
			if (graph.containsKey(cur)) {
				for (int[] child : graph.get(cur)) {// investigate all children
					int dest = child[0];
					int weight = child[1];
					dist.put(dest, Math.min(dist.get(dest), dist.get(cur) + weight));// make a choice - either leave as is OR use path
				}
			}
		}

		int ans = 0;
		for (int cand : dist.values()) {
			if (cand == Integer.MAX_VALUE) {
				return -1;
			}
			ans = Math.max(ans, cand);
		}
		return ans;
	}
}
