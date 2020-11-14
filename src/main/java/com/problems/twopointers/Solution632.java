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
 * IDEA:
 * for each k lists generate a ListElemTracker tracker
 * then find interval = {Best{left_edge_i},globalMaxAcrossAllLists}
 * in the beginning:
 * globalMaxAcrossAllLists for i=0 => 5
 * [4,5]
 * [0,5] - covers everything
 * [5,5]
 * 
 */
public class Solution632 {
    
    static class ListElemTracker {
        int id;
        int left;
        int pos;
        int size;

        public ListElemTracker(int id, int left, int pos, int size) {
            this.id = id;// ref to the list
            this.left = left;// num[0] in current contiguous list
            this.pos = pos;  // index of elem in this list
            this.size = size;
        }

    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<ListElemTracker> pq = new PriorityQueue<>((a, b) -> a.left - b.left);// natural sorting by 1st elem, the smallest on the top
        int globalMax = Integer.MIN_VALUE;
        int globalLeft = 0;
        int globalRight = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); ++i) {
        	int firstElem = nums.get(i).get(0);
            pq.add(new ListElemTracker(i, firstElem, 0, nums.get(i).size()));// fill with Lists with pos=0
            globalMax = Math.max(globalMax, firstElem);// max value across all lists
        }
        // globalMax now contains the max accross all elems
        // the 
        while(true) {// we are going through each of n lists
            ListElemTracker p = pq.poll();
            if (globalMax - p.left < globalRight - globalLeft) {// if there is a smaller range,  [p.left, globalMax], reset
                globalLeft = p.left;
                globalRight = globalMax;
            }
            int next = p.pos + 1;
            if (next < p.size) {// not end of list, continue cycle
            	int nextElem = nums.get(p.id).get(next);
                pq.add(new ListElemTracker(p.id, nextElem, next, p.size));// replace this list with the same id, different elem
                globalMax = Math.max(globalMax, nextElem);
            }else {
                break;
            }
        }
        return new int[]{globalLeft, globalRight};
    }

}
