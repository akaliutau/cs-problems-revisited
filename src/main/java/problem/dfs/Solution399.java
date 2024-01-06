package problem.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * You are given an array of variable pairs equations and an array of real
 * numbers values, where equations[i] = [Ai, Bi] and values[i] represent the
 * equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a
 * single variable.
 * 
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the
 * jth query where you must find the answer for Cj / Dj = ?.
 * 
 * Return the answers to all queries. If a single answer cannot be determined,
 * return -1.0.
 * 
 * Note: The input is always valid. You may assume that evaluating the queries
 * will not result in division by zero and that there is no contradiction.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries =
 * [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]] Output:
 * [6.00000,0.50000,-1.00000,1.00000,-1.00000] Explanation: Given: a / b = 2.0,
 * b / c = 3.0 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x =
 * ? return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * IDEA:
 * 
 * a/c = a/b * b/c
 * 
 * have : a/b, b/c, b/d =>
 * a/b, b/a, b/c, c/b, b/d, d/b
 * 
 * a -> b
 * b -> [a,c,d]
 * 
 * 
 * 
 */
public class Solution399 {

	class Elem {
		String val;
		boolean visited;
		Map<Elem, Double> adjList;

		Elem(String val) {
			this.val = val;
			visited = false;
			adjList = new HashMap<>();
		}
	}

	public HashMap<String, Elem> nodes;

	public double dfs(Elem start, Elem end, double value) {
		if (start == null || end == null) {
			return -1;
		}
		if (start.val.equals(end.val)) {
			return value;
		}
		start.visited = true;
		for (Entry<Elem, Double> entry : start.adjList.entrySet()) {
			Elem node = entry.getKey();
			if (!node.visited) {
				double res = dfs(node, end, value * start.adjList.get(node));
				if (res != -1) {
					return res;
				}
			}
		}
		return -1;
	}

	public void resetNodes() {
		for (Map.Entry<String, Elem> entry : nodes.entrySet()) {
			entry.getValue().visited = false;
		}
	}

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		nodes = new HashMap<>();
		int i = 0;
		int n = queries.size();

		for (List<String> equation : equations) {
			String numerator = equation.get(0);
			String denominator = equation.get(1);
			double value = values[i++];
			if (!nodes.containsKey(numerator)) {
				nodes.put(numerator, new Elem(numerator));
			}
			if (!nodes.containsKey(denominator)) {
				nodes.put(denominator, new Elem(denominator));
			}
			nodes.get(numerator).adjList.put(nodes.get(denominator), value);
			nodes.get(denominator).adjList.put(nodes.get(numerator), 1.0 / value);
		}

		double res[] = new double[n];
		i = 0;
		for (List<String> query : queries) {
			resetNodes();
			Elem startNode = nodes.get(query.get(0));
			Elem endNode = nodes.get(query.get(1));
			res[i++] = dfs(startNode, endNode, 1.0);
		}
		return res;
	}

}
