package com.problems.greedy;

import java.util.Arrays;

/**
 * Given a collection of intervals, find the minimum number of intervals you
 * need to remove to make the rest of the intervals non-overlapping. Example 1:
 * Input: [[1,2],[2,3],[3,4],[1,3]] Output: 1 Explanation: [1,3] can be removed
 * and the rest of intervals are non-overlapping.
 * 
 * IDEA:
 *  consider continuous removal: 
 *  1) collect the statistics about overlaps for each interval
 *  2) sort them
 *  [1,2],[2,3],[3,4],[1,3] =>
 *  
 *  [1,2],[1,3],[2,3],[3,4]
 *  
 *  
 *  O(n^2) for stat
 *  O(n^2) for iterative removal
 */
public class Solution435 {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 1 || intervals[0].length != 2) {
            return 0;
        }
        Arrays.parallelSort(intervals, (o, p) -> o[0] - p[0]);

        int overlaps = 0;
        int end = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++){
            if (intervals[i][0] < end){// interval started before previous finished
                overlaps ++;
                end = Math.min(end, intervals[i][1]);
            }else{
                end = intervals[i][1];
            }
        }
        
        return overlaps;    
    }

}
