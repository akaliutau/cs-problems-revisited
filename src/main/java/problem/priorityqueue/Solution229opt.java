package problem.priorityqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array of size n, find all elements that appear more than 
 * n/3  times. Example 1: Input: nums = [3,2,3] Output: [3]
 * 
 * [1,1,1,2,3,4,5]
 * 
 * counter for 1:
 *  1 2 3 3 3 3 3 
 * counter for 2:
 *        1 0 
 *          |
 *          2 => 3
 *  IDEA:
 *  1) optimal - O(1) in space: use queue with fixed size (2 to be exact) because one can have only 2 elems with freq > 1/3 (total > 2/3)
 *  2) suboptimal - O(n) in space: use statistics structure 
 *  
 */
public class Solution229opt {
	
	static class Elem {
		public int num;
		public int freq = 1;
		
		public Elem(int num) {
			this.num = num;
		}
	}

    public List<Integer> majorityElement(int[] nums) {

        PriorityQueue<Elem> pq = new PriorityQueue<>((o,p) -> o.freq - p.freq);
        Map<Integer,Elem> queued = new HashMap<>();

        for (int num : nums) {
        	if (queued.containsKey(num)) {
        		queued.get(num).freq ++;
        	}else if (queued.size() < 2){
        		Elem elem = new Elem(num);
        		queued.put(num, elem);
        		pq.add(elem);
        	}else {// found new key which overflows queue
        		Elem elem = pq.poll();
        		queued.remove(elem.num);
        		elem = new Elem(num);
        		queued.put(num, elem);
        		pq.add(elem);
        	}
        }
		for (Elem elem : pq) {
			elem.freq = 0;
		}
		
        for (int num : nums) {
        	if (queued.containsKey(num)) {
        		queued.get(num).freq ++;
        	}
        }

        int n = nums.length;
		List<Integer> result = new ArrayList<>();
		for (Elem elem : pq) {
			if (elem.freq  > n /3) {
				result.add(elem.num);
			}
		}
        return result;
    }

}
