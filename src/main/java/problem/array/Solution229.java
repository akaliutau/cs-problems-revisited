package problem.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Solution229 {

    public List<Integer> majorityElement(int[] nums) {

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
