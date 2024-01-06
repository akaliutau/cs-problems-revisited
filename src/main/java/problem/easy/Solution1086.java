package problem.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a list of the scores of different students, items, where items[i] =
 * [IDi, scorei] represents one score from a student with IDi, calculate each
 * student's top five average.
 * 
 * Return the answer as an array of pairs result, where result[j] = [IDj,
 * topFiveAveragej] represents the student with IDj and their top five average.
 * Sort result by IDj in increasing order.
 * 
 * A student's top five average is calculated by taking the sum of their top
 * five scores and dividing it by 5 using integer division.
 *
 */
public class Solution1086 {
	public int[][] highFive(int[][] items) {

		Map<Integer, PriorityQueue<Integer>> stat = new HashMap<>();

		for (int[] res : items) {
			stat.computeIfAbsent(res[0], k -> new PriorityQueue<>()).add(res[1]);
			PriorityQueue<Integer> pq = stat.get(res[0]);
			if (pq.size() > 5) {
				pq.poll();
			}
		}

		int[][] ret = new int[stat.size()][2];
		int idx = 0;
		for (int id : stat.keySet()) {
			final int[] marks = new int[1];
			stat.get(id).forEach(mark -> {
				marks[0] += mark;
			});
			ret[idx++] = new int[] { id, marks[0] / stat.get(id).size() };
		}
		return ret;
	}
}
