package problem.twopointers;

import java.util.List;
import java.util.PriorityQueue;

/**
 * You have k lists of sorted integers in non-decreasing order. Find the
 * smallest range that includes at least one number from each of the k lists. 
 * 
 * We define the range [a, b] is smaller than range  [c, d] 
 * if b - a < d - c or // shorter
 * a < c if b - a == d - c. // nummost if equals in length
 * 
 * Example 1: Input: nums =  [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]] Output: [20,24] 
 * 
 * Explanation: 
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24]. 
 * List 2: [0, 9, 12, 20], 20 is in range [20,24]. 
 * List 3: [5, 18, 22, 30], 22 is in range [20,24]
 * 
 * IDEA:
 * 
 * for each k lists generate a ListElemTracker tracker
 * then find interval = {Best{num_edge_i}, globalMaxAccrossAllLists}
 * 
 * in the beginning:
 * 
 * globalMaxAccrossAllListsAcrossAllLists for i=0 => max(4, 0, 5) = 5, =>
 * 
 * [4,5]
 * [0,5] - covers everything because 0 = (min across all intervals) (due to PriorityQueue feature)
 * [5,5]
 * 
 *  a. pick up the sequence with the smallest number -it will be the leftmost => 
 *  b. the range {p.getElem(), globalMaxAccrossAllLists} will cover everything by definition
 *
 * 
 * use PriorityQueue to pick  up the leftmost interval across all lists
 * 
 * NOTE: if the elem in some line is not present (f.e. 22 is not in [20,24], the process will be [20,22] -> [20,24])
 */
public class Solution632 {
    
    static class ListElemTracker {
        List<Integer> num;
        int pos = 0;

        public ListElemTracker(List<Integer> num) {
            this.num = num;// the nummost number (i.e. num[0]) in current contiguous list
        }
        
        public ListElemTracker clone() {
        	ListElemTracker copy = new ListElemTracker(this.num);
        	copy.pos = this.pos + 1;
        	return copy;
        }
        
        public int getElem() {
        	return num.get(pos);
        }
        
        public boolean hasNext() {
        	return pos < num.size() - 1;
        }

    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<ListElemTracker> pq = new PriorityQueue<>((a, b) -> a.getElem() - b.getElem());// natural sorting by 1st elem, the smallest on the top
        int globalMaxAccrossAllLists = Integer.MIN_VALUE;
        int bestLeft = 0;
        int bestRight = Integer.MAX_VALUE;
        // init cycle
        for (int i = 0; i < nums.size(); ++i) {
            pq.add(new ListElemTracker(nums.get(i)));// fill with Lists with pos=0
            globalMaxAccrossAllLists = Math.max(globalMaxAccrossAllLists, nums.get(i).get(0));// max value across all lists
        }
        // globalMaxAccrossAllLists now contains the max across all elems
        // we already have an intermediate answer
        
        while(true) {// we are going through each of n lists
        	// pick up the sequence with the smallest number -it will be the leftmost => 
        	// the range {p.getElem(), globalMaxAccrossAllLists} will cover everything by definition
            ListElemTracker p = pq.poll();
            if (globalMaxAccrossAllLists - p.getElem() < bestRight - bestLeft) {// if there is a smaller range,  [p.num, globalMaxAccrossAllLists], reset NOTE: on equals intervals we are leaving all unchanged
                bestLeft = p.getElem();// 0
                bestRight = globalMaxAccrossAllLists;// 5
             // [0,5] covers everything
            }
            
            if (p.hasNext()) {// not the end of list, continue
            	ListElemTracker nextElem = p.clone();
            	nextElem.pos ++;
                pq.add(nextElem);
                globalMaxAccrossAllLists = Math.max(globalMaxAccrossAllLists, nextElem.getElem());
            }else {// early exit on end of ANY list
                break;
            }
        }
        return new int[]{bestLeft, bestRight};
    }

}
