package problem.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of events where events[i] = [startDayi, prevEndDayi,
 * valuei]. The ith event starts at startDayi and prevEnds at prevEndDayi, and if you
 * attend this event, you will receive a value of valuei. You are also given an
 * integer k which represents the maximum number of events you can attend.
 * 
 * You can only attend one event at a time. If you choose to attend an event,
 * you must attend the entire event. Note that the prevEnd day is inclusive: that
 * is, you cannot attend two events where one of them starts and the other prevEnds
 * on the same day.
 * 
 * Return the maximum sum of values that you can receive by attending events.
 * 
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2 Output: 7 Explanation:
 * Choose the green events, 0 and 1 (0-posed) for a total value of 4 + 3 = 7.
 * 
 * 
 * IDEA:
 * optimized BF + DFS + memoization
 * 
 * 1. try to form ALL possible seqLengths taking into account all constraints laid
 * 
 * Here is a detail example:
 * 
 *   initial: [4,3,1]
 *   
 *     4      3
 *   -----   -----
 *         1
 *      -------
 *      
 *  2. use greedy approach - always take the immediate NEXT seqLength, because
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
	
	int dfs(int[][] events, int pos, int seqLength, int k, int prevEnd, Map<String, Integer> memo) {
		if (seqLength == k || pos == events.length) {
			return 0;
		}

		String key = seqLength + ":" + prevEnd;
		if (memo.get(key) != null) {
			return memo.get(key);
		}

		// make choice: 
		// 1) either drop event: events[pos]
		// 2) include events[pos] into sequence
		
		// drop event at pos continue at pos + 1
		// Note: seqLength counter does not increase
		int max = dfs(events, pos + 1, seqLength, k, prevEnd, memo);
		
		// if possible to chain to current seqLength, then chain it! 
		int eventStart = events[pos][0];
		int eventEnd = events[pos][1];
		
		if (eventStart > prevEnd) {
			int curValue = events[pos][2];
			max = Math.max(max, 
						curValue + dfs(events, pos + 1, seqLength + 1, k, eventEnd, memo)
							);
		}

		memo.put(key, max);
		return max;
	}

	public int maxValue(int[][] events, int k) {
		Arrays.sort(events, (o, p) -> o[0] == p[0] ? o[1] - p[1] : o[0] - p[0]);//by start, then shorter intervals go first

		// <seqLength + ":" + end => cost>
		Map<String, Integer> memo = new HashMap<>();

		return dfs(events, 0, 0, k, 0, memo);
	}

}
