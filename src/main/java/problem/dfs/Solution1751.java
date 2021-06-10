package problem.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi,
 * valuei]. The ith event starts at startDayi and ends at endDayi, and if you
 * attend this event, you will receive a value of valuei. You are also given an
 * integer k which represents the maximum number of events you can attend.
 * 
 * You can only attend one event at a time. If you choose to attend an event,
 * you must attend the entire event. Note that the end day is inclusive: that
 * is, you cannot attend two events where one of them starts and the other ends
 * on the same day.
 * 
 * Return the maximum sum of values that you can receive by attending events.
 * 
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2 Output: 7 Explanation:
 * Choose the green events, 0 and 1 (0-posed) for a total value of 4 + 3 = 7.
 * 
 * 
 * IDEA:
 * 1. try to form ALL possible segments taking into account all constraints laid
 *   initial: [4,3,1]
 *   
 *     4      3
 *   -----   -----
 *         1
 *      -------
 *      
 *  2. use greedy approach - always take the immediate NEXT segment, because
 *  
 *     when we consider 
 *     0 -> 1, val = 14 
 *     1 -> 2(None), val = 13 
 *     0 -> 1, val = 14 
 *     
 *         #0       #1        #2
 *     [[1,2,1],[3,7,13],[5,6,14]]
 *     
 *     -
 *     2
 *     1
 *     0->2
 *     0->1
 *     
 *   
 */
public class Solution1751 {
	
	int dfs(int[][] events, int pos, int segment, int k, int end, Map<String, Integer> memo) {
		if (segment == k || pos == events.length) {
			return 0;
		}

		String key = segment + ":" + end;
		if (memo.get(key) != null) {
			return memo.get(key);
		}

		// skip segment @ pos and continue @ pos + 1
		// Note: segment counter does not increase
		int max = dfs(events, pos + 1, segment, k, end, memo);
		
		// if possible to chain to current segment, then chain it! 
		if (events[pos][0] > end) {
			int curValue = events[pos][2];
			max = Math.max(max, 
						curValue + dfs(events, pos + 1, segment + 1, k, events[pos][1], memo)
							);
		}

		memo.put(key, max);
		return max;
	}

	public int maxValue(int[][] events, int k) {
		Arrays.sort(events, (o, p) -> o[0] == p[0] ? o[1] - p[1] : o[0] - p[0]);//by start, then shorter intervals go first

		// <segment + ":" + end, sum>
		Map<String, Integer> memo = new HashMap<>();

		return dfs(events, 0, 0, k, 0, memo);
	}

}
