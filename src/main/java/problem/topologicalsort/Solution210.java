package problem.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There are a total of n courses you have to take labelled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example, if prerequisites[i] = [ai,
 * bi] this means you must take the course bi before the course ai.
 * 
 * Given the total number of courses numCourses and a list of the prerequisite
 * pairs, return the ordering of courses you should take to finish all courses.
 * 
 * If there are many valid answers, return any of them. If it is impossible to
 * finish all courses, return an empty array.
 * 
 * IDEA:
 * 1. build dynamic statistics hash-table dependencies containing statistics about dependencies
 * 2. use graph in the form of hash map  dep => list of courses for fast access - acceleration
 * 
 */
public class Solution210 {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		int[] dependencies = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int course = prerequisites[i][0];
			int dep = prerequisites[i][1];
			graph.computeIfAbsent(dep, k -> new ArrayList<>()).add(course);

			// Record in-degree of each vertex
			dependencies[course] ++;
		}

		// Add all vertices with 0 dependencies to the queue
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (dependencies[i] == 0) {
				q.add(i);// only courses without dependencies will be added
			}
		}

		int i = 0;
		// Process until the Q becomes empty
		while (!q.isEmpty()) {
			int node = q.poll();
			topologicalOrder[i++] = node; // add to the result

			// after course removal update dependencies
			if (graph.containsKey(node)) {
				for (Integer neighbor : graph.get(node)) {// reduce the dependency index (in-degree) of each neighbor by 1
					dependencies[neighbor]--;

					// If dependency index of a neighbor becomes 0, add it to the Q
					if (dependencies[neighbor] == 0) {
						q.add(neighbor);
					}
				}
			}
		}

		// Check to see if topological sort is possible or not.
		if (i == numCourses) {
			return topologicalOrder;
		}

		return new int[0];

	}

}
