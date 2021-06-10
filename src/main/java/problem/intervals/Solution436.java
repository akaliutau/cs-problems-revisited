package problem.intervals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and
 * each starti is unique. The right interval for an interval i is an interval j
 * such that startj >= endi and startj is minimized. Return an array of right
 * interval indices for each interval i. If no right interval exists for
 * interval i, then put -1 at index i. 
 * 
 * Example 1: Input: intervals = [[1,2]]
 * Output: [-1] Explanation: There is only one interval in the collection, so it
 * outputs -1. 
 * 
 * Example 2: Input: intervals = [[3,4],[2,3],[1,2]] Output:
 * [-1,0,1] Explanation: There is no right interval for [3,4]. The right
 * interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >=
 * end1 = 3. The right interval for [1,2] is [2,3] since start1 = 2 is the
 * smallest start that is >= end2 = 2.
 * 
 * IDEA:
 * 
 * 1. sort intervals by start edge
 * 2. 
 * 
 */
public class Solution436 {

    public int[] findRightInterval(int[][] intervals) {
        
        int n = intervals.length;
        Map<int[],Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(intervals[i], i);
        }        
        Arrays.parallelSort(intervals, (o, p) -> o[0] - p[0]);// modify original list of intervals
        
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = -1;
            for (int j = i + 1; j < n; j++) {// iterate through all the rest intervals
            	int start = intervals[j][0];
            	int endPrev = intervals[i][1];
                if (start >= endPrev) {// stop here
                    idx = index.get(intervals[j]);
                    break;
                }
            }
            ans[index.get(intervals[i])] = idx;
        }

        return ans;
    }

}
