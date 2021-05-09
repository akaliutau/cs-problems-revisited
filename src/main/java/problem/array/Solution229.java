package problem.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 *  use queue with fixed size (2 to be exact)
 *  
 */
public class Solution229 {

    public List<Integer> majorityElement(int[] nums) {

        // 1st pass
        Map<Integer,Integer> queue = new HashMap<>();// simulate the key of size 2

        for (int num : nums) {
        	if (queue.containsKey(num)) {
        		queue.compute(num, (k,v) -> v + 1);
        	}else if (queue.size() < 2){
        		queue.put(num, 1);
        	}else {
        		Set<Integer> toRemove = new HashSet<>();
        		for (Integer key : queue.keySet()) {
        			if (queue.get(key) == 0) {
        				toRemove.add(key);
        			}
        			queue.compute(key, (k,v) -> v - 1);
        		}
        		for (Integer key : toRemove) {
        			queue.remove(key);
        		}
        	}
        }
        // 2nd pass
        Map<Integer,Integer> stat = new HashMap<>();
        

        // 2nd pass
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
        	stat.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        int n = nums.length;
		for (Integer key : stat.keySet()) {
			if (stat.get(key)  > n /3) {
				result.add(key);
			}
		}
        return result;
    }

}
