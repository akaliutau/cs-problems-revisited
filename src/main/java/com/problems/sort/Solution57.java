package com.problems.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary). You may assume that the intervals were
 * initially sorted according to their start times. 
 * 
 * Example 1: Input: intervals
 * = [[1,3],[6,9]], newInterval = [2,5] Output: [[1,5],[6,9]]
 * 
 * IDEA:
 * 1) omit all arrays that ended before
 * 2) if array falls in gap, add it and replace newInterval with current one (all the rest will be chained)
 * 3) merge all intersection
 */
public class Solution57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int[] in : intervals) {
            if (in[1] < newInterval[0]) {
                res.add(in);
            } else if (newInterval[1] < in[0]) {
                res.add(newInterval);
                newInterval = in;
            } else {
                newInterval[0] = Math.min(newInterval[0], in[0]);
                newInterval[1] = Math.max(newInterval[1], in[1]);
            }
        }
        res.add(newInterval);

        return res.toArray(new int[res.size()][]);

    }

}
