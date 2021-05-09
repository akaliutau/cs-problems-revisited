package problem.twopointers;

import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in non-decreasing order. Find the
 * smallest range that includes at least one number from each of the k lists. 
 * 
 * We define the range [a, b] is smaller than range  [c, d] 
 * if b - a < d - c or // shorter
 * a < c if b - a == d - c. // leftmost if equals in length
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
 * then find interval = {Best{left_edge_i}, globalMaxAcrossAllLists}
 * 
 * in the beginning:
 * globalMaxAcrossAllLists for i=0 => max(4, 0, 5) = 5, =>
 * [4,5]
 * [0,5] - covers everything because 0 = (min across all intervals) (due to PriorityQueue feature)
 * [5,5]
 * 
 * On the second move the list #1 will be picked up with elem=4 and globalRight = 9
 * i.e. we have a transition [0,5] -> [4,9] which is still covering all lines, i.e. replaced 5 -> 9 and 0 -> 4
 * 
 * use PriorityQueue to pick  up the leftmost interval across all lists
 * if the elem in some line is not present (f.e. 22 is not in [20,24], the process will be [20,22] -> [20,24])
 */
public class Solution632 {
    
    static class ListElemTracker {
        int id;
        int left;
        int pos;
        int size;

        public ListElemTracker(int id, int left, int pos, int size) {
            this.id = id;// ref to the list
            this.left = left;// the leftmost number (i.e. num[0]) in current contiguous list
            this.pos = pos;  // index of elem in this list
            this.size = size;
        }

    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<ListElemTracker> pq = new PriorityQueue<>((a, b) -> a.left - b.left);// natural sorting by 1st elem, the smallest on the top
        int globalMax = Integer.MIN_VALUE;
        int globalLeft = 0;
        int globalRight = Integer.MAX_VALUE;
        // init cycle
        for (int i = 0; i < nums.size(); ++i) {
        	int firstElem = nums.get(i).get(0);
            pq.add(new ListElemTracker(i, firstElem, 0, nums.get(i).size()));// fill with Lists with pos=0
            globalMax = Math.max(globalMax, firstElem);// max value across all lists
        }
        // globalMax now contains the max across all elems
        
        // main cycle
        while(true) {// we are going through each of n lists
            ListElemTracker p = pq.poll();
            if (globalMax - p.left < globalRight - globalLeft) {// if there is a smaller range,  [p.left, globalMax], reset NOTE: on equals intervals we are leaving all unchanged
                globalLeft = p.left;// 0
                globalRight = globalMax;// 5
             // [0,5] covers everything
            }
            
            int next = p.pos + 1;
            if (next < p.size) {// not the end of list, continue
            	int nextElem = nums.get(p.id).get(next);
                pq.add(new ListElemTracker(p.id, nextElem, next, p.size));// replace this list with the SAME id, but DIFFERENT elem
                globalMax = Math.max(globalMax, nextElem);
            }else {// exit on end of ANY list
                break;
            }
        }
        return new int[]{globalLeft, globalRight};
    }

}
