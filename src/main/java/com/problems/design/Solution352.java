package com.problems.design;

import java.util.TreeMap;

/**
 * 
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals. 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ...,
 * then the summary will be: 
 * 1 [1, 1] 
 * 3 [1, 1], [3, 3] 
 * 7 [1, 1], [3, 3], [7, 7] 
 * 2 [1, 3], [7, 7]              // case 1
 * 6 [1, 3], [6, 7]              // case 2
 * 
 */
public class Solution352 {

    class SummaryRanges {

        private TreeMap<Integer, Integer> intervals;// [start => end] describes [start, end]

        /** Initialize your data structure here. */
        public SummaryRanges() {
            intervals = new TreeMap<>();// map with ordered keys
        }

        public void addNum(int val) {

            if (intervals.containsKey(val))// coincides with interval boundaries
                return;

            Integer prev = intervals.lowerKey(val);// lower next to val
            Integer next = intervals.higherKey(val);// higher next to val

            if (prev != null && next != null && intervals.get(prev) == val - 1 && next  == val + 1) {// merge existing interval
                intervals.put(prev, intervals.get(next));
                intervals.remove(next);
            } else if (next != null && next == val + 1) {// extend existing interval
                intervals.put(val, intervals.get(next));
                intervals.remove(next);
            } else if (prev != null && intervals.get(prev) >= val - 1) {
                intervals.put(prev, Math.max(val, intervals.get(prev)));
            } else {
                intervals.put(val, val);
            }
        }

        public int[][] getIntervals() {
            int count = intervals.size();
            int[][] result = new int[count][2];

            int i = 0;
            for (int key : intervals.keySet())
                result[i++] = new int[] { key, intervals.get(key) };
            return result;
        }
    }

 
}
