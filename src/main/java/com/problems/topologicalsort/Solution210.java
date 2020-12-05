package com.problems.topologicalsort;

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
 */
public class Solution210 {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		int[] indegree = new int[numCourses];
		int[] topologicalOrder = new int[numCourses];

		// Create the adjacency list representation of the graph
		for (int i = 0; i < prerequisites.length; i++) {
			int dest = prerequisites[i][0];
			int src = prerequisites[i][1];
			adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);

			// Record in-degree of each vertex
			indegree[dest] += 1;
		}

		// Add all vertices with 0 in-degree to the queue
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		int i = 0;
		// Process until the Q becomes empty
		while (!q.isEmpty()) {
			int node = q.remove();
			topologicalOrder[i++] = node;

			// Reduce the in-degree of each neighbor by 1
			if (adjList.containsKey(node)) {
				for (Integer neighbor : adjList.get(node)) {
					indegree[neighbor]--;

					// If in-degree of a neighbor becomes 0, add it to the Q
					if (indegree[neighbor] == 0) {
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
