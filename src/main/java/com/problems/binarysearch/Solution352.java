package com.problems.binarysearch;

import java.util.TreeMap;

/**
 *
 */
public class Solution352 {

    class SummaryRanges {

        private TreeMap<Integer, Integer> intervals;

        /** Initialize your data structure here. */
        public SummaryRanges() {
            intervals = new TreeMap<>();
        }

        public void addNum(int val) {

            if (intervals.containsKey(val))
                return;

            Integer lowerKey = intervals.lowerKey(val);
            Integer higherKey = intervals.higherKey(val);

            if (lowerKey != null && higherKey != null && intervals.get(lowerKey) + 1 == val && higherKey - 1 == val) {
                intervals.put(lowerKey, intervals.get(higherKey));
                intervals.remove(higherKey);
            } else if (higherKey != null && higherKey - 1 == val) {
                intervals.put(val, intervals.get(higherKey));
                intervals.remove(higherKey);
            } else if (lowerKey != null && intervals.get(lowerKey) + 1 >= val) {
                intervals.put(lowerKey, Math.max(val, intervals.get(lowerKey)));
            }else {
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

    public static void main(String[] arg) {

        System.out.println();

    }

}
