package com.problems.twopointers;

import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in non-decreasing order. Find the
 * smallest range that includes at least one number from each of the k lists. We
 * define the range [a, b] is smaller than range [c, d] if b - a < d - c or a <
 * c if b - a == d - c. 
 * 
 * Example 1: Input: nums =  [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]] Output: [20,24] 
 * 
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. 
 * List 2: [0, 9, 12, 20], 20 is in range [20,24]. 
 * List 3: [5, 18, 22, 30], 22 is in range [20,24]
 * 
 */
public class Solution632 {
    
    static class LList {
        int id;
        int left;
        int idx;

        public LList(int id, int left, int idx) {
            this.id = id;
            this.left = left;
            this.idx = idx;
        }

    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<LList> pq = new PriorityQueue<>((a, b) -> a.left - b.left);// natural sorting by 1st elem, the smallest on the top
        int globalMax = Integer.MIN_VALUE;
        int globalLeft = 0;
        int globalRight = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); ++i) {
            pq.add(new LList(i, nums.get(i).get(0), 0));// fill with Lists with idx=0
            globalMax = Math.max(globalMax, nums.get(i).get(0));// max value across all lists
        }
        while(true) {// we are going through each of n lists
            LList p = pq.poll();
            if (globalMax - p.left < globalRight - globalLeft) {// if there is a smaller range,  [p.left, globalMax], reset
                globalLeft = p.left;
                globalRight = globalMax;
            }
            int next = p.idx + 1;
            if (next < nums.get(p.id).size()) {// not end of list, continue cycle
                globalMax = Math.max(globalMax, nums.get(p.id).get(next));
                pq.add(new LList(p.id, nums.get(p.id).get(next), next));// replace this list with the same id, different elem
            }else {
                break;
            }
        }
        return new int[]{globalLeft, globalRight};
    }

}
