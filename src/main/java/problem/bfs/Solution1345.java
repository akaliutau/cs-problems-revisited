package problem.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * Given an array of integers arr, you are initially positioned at the first
 * index of the array.
 * 
 * In one step you can jump from index i to index:
 * 
 * i + 1 where: i + 1 < arr.length. 
 * i - 1 where: i - 1 >= 0. 
 * j where: arr[i] == arr[j] and i != j. 
 * 
 * Return the minimum number of steps to reach the last index
 * of the array.
 * 
 * Notice that you can not jump outside of the array at any time.
 * 
 * Example 1:
 * 
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404] Output: 3
 * 
 * IDEA:
 * 1. use numbers as nodes and BFS to find the fastest route
 * 2. start propagation process from each node
 * 3. use mapping // node_value => [list of refs] to access fast routes
 *
 */
public class Solution1345 {
	public int minJumps(int[] arr) {
		int n = arr.length;
		if (n <= 1) {// edge case
			return 0;
		}
		// preparations
		Map<Integer, List<Integer>> graph = new HashMap<>();// node_value => [list of node Ids]
		for (int i = 0; i < n; i++) {
			graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
		}

		Queue<Integer> q = new LinkedList<>(); // store current layer
		q.add(0);
		Set<Integer> visited = new HashSet<>();
		int step = 0;

		// when current layer exists
		while (!q.isEmpty()) {
			List<Integer> next = new LinkedList<>();

			// iterate the layer
			for (int node : q) {
				// check if reached end
				if (node == n - 1) {
					return step;
				}

				// check same value
				List<Integer> connected = graph.get(arr[node]); 
				for (int child : connected) {
					if (!visited.contains(child)) {
						visited.add(child);
						next.add(child);
					}
				}

				// clear the list to prevent redundant search
				graph.get(arr[node]).clear();

				// check neighbors
				if (node + 1 < n && !visited.contains(node + 1)) {
					visited.add(node + 1);
					next.add(node + 1);
				}
				if (node - 1 >= 0 && !visited.contains(node - 1)) {
					visited.add(node - 1);
					next.add(node - 1);
				}
			}
            q.clear();
			q.addAll(next);
			step++;
		}
		return -1;
	}
}
