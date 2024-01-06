package problem.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of meeting time intervals consisting of start and end times
 * [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms
 * required.
 * 
 * Example 1:
 * 
 * Input: [[0, 30],[5, 10],[15, 20]] Output: 2
 * 
 * IDEA:
 * dynamically update endings using PQ to track the earliest ending
 *  [0,                  30]
 *       [5, 10]                <-- this one is ended @10 < 30
 *               [15, 20]       <-- can be dropped because 15 > 10
 * 
 */
public class Solution253 {

	public int minMeetingRooms(int[][] intervals) {
		int len = intervals.length;

		// Check for the base case. If there are no intervals, return 0
		if (len == 0) {
			return 0;
		}

		// Min heap - the top contains the min ending of ADDED intervals
		Comparator<Integer> byEnd = (o, p) -> Integer.compare(o, p);
		Queue<Integer> endings = new PriorityQueue<Integer>(len, byEnd);

		// Sort the intervals by start time
		Comparator<int[]> byStart = (o, p) -> Integer.compare(o[0], p[0]);
		Arrays.sort(intervals, byStart);

		// Add the first meeting
		endings.add(intervals[0][1]);

		// Iterate over remaining intervals
		for (int i = 1; i < len; i++) {
			int start = intervals[i][0];
			int end = intervals[i][1];
			// If the ending of {all added intervals} already in the past relatively
			// interval [i], use that time slot
			if (start >= endings.peek()) {// end of all previous ones if and only if it ended earlier
				endings.poll();// drop the previous interval
			}
			endings.add(end);
		}

		// The size of the heap tells us the minimum slots required for all the
		// meetings.
		return endings.size();
	}


}
