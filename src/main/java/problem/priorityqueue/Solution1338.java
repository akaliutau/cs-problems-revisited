package problem.priorityqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * Given an array arr. You can choose a set of integers and remove all the
 * occurrences of these integers in the array.
 * 
 * Return the minimum size of the set so that at least half of the integers of
 * the array are removed.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [3,3,3,3,5,5,5,2,2,7] Output: 2 
 * 
 * Explanation: Choosing {3,7} will
 * make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the
 * size of the old array). Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array
 * [3,3,3,3,5,5,5] which has size greater than half of the size of the old
 * array.
 * 
 * IDEA:
 * 1. collect statistics
 * 2. use PriorityQueue to pick up the few top sets
 *
 */
public class Solution1338 {
	public int minSetSize(int[] arr) {
		Map<Integer, Integer> statMap = new HashMap<>();
		for (int num : arr) {
			statMap.compute(num, (k, v) -> v == null ? 1 : v + 1);
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o, p) -> p[1] - o[1]);
		for (Integer num : statMap.keySet()) {
			pq.add(new int[] { num, statMap.get(num) });
		}
		int n = arr.length;
		int setSize = 0;
		while (!pq.isEmpty()) {
			int[] set = pq.poll();
			n -= set[1];
			setSize++;
			if (n <= arr.length / 2) {
				break;
			}
		}

		return setSize;

	}

}
