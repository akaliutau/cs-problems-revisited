package problem.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
 * all possible paths from node 0 to node n - 1, and return them in any order.
 * 
 * The graph is given as follows: graph[i] is a list of all nodes you can visit
 * from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * 
 * Input: graph = [[1,2],[3],[3],[]] Output: [[0,1,3],[0,2,3]] Explanation:
 * There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
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
		results = new ArrayList<>();
		// adopt the LinkedList for fast access to the tail element.
		LinkedList<Integer> path = new LinkedList<>();
		path.addLast(0);
		// kick of the backtracking, starting from the source (node 0)
		backtrack(0, path);
		return results;
	}



}
