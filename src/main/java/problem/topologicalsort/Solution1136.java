package problem.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * You are given an integer n, which indicates that there are n courses labeled
 * from 1 to n. You are also given an array relations where relations[i] =
 * [prevCoursei, nextCoursei], representing a prerequisite relationship between
 * course prevCoursei and course nextCoursei: course prevCoursei has to be taken
 * before course nextCoursei.
 * 
 * In one semester, you can take any number of courses as long as you have taken
 * all the prerequisites in the previous semester for the courses you are
 * taking.
 * 
 * Return the minimum number of semesters needed to take all courses. If there
 * is no way to take all the courses, return -1.
 * 
 * IDEA:
 * 1. perform a classical topological sort
 * 2. count the number of rounds (layers)
 * 
 *
 */
public class Solution1136 {

	public int minimumSemesters(int n, int[][] relations) {
		int[] inCount = new int[n + 1];
		List<List<Integer>> graph = new ArrayList<>(n + 1);
		for (int i = 0; i < n + 1; ++i) {
			graph.add(new ArrayList<>());
		}
		for (int[] relation : relations) {
			graph.get(relation[0]).add(relation[1]);
			inCount[relation[1]]++;
		}
		int step = 0;
		int studiedCount = 0;
		Queue<Integer> toDo = new LinkedList<>();
		for (int node = 1; node < n + 1; node++) {
			if (inCount[node] == 0) {
				toDo.add(node);
			}
		}
		// start learning with BFS
		while (!toDo.isEmpty()) {
			// start new semester
			step++;
			int l = toDo.size();
			for (int i = 0; i < l; i++) {
				Integer node = toDo.poll();
				studiedCount++;
				for (int endNode : graph.get(node)) {
					inCount[endNode]--;
					// if all prerequisite courses learned
					if (inCount[endNode] == 0) {
						toDo.add(endNode);
					}
				}
			}
		}

		// check if learn all courses
		return studiedCount == n ? step : -1;
	}

}
