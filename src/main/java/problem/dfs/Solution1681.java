package problem.dfs;

import java.util.Arrays;

/**
 * You are given an integer array nums​​​ and an integer k. You are asked to
 * distribute this array into k subsets of equal size such that there are no two
 * equal elements in the same subset.
 * 
 * A subset's incompatibility is the difference between the maximum and minimum
 * elements in that array.
 * 
 * Return the minimum possible sum of incompatibilities of the k subsets after
 * distributing the array optimally, or return -1 if it is not possible.
 * 
 * A subset is a group integers that appear in the array with no particular
 * order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,1,4], k = 2 Output: 4 
 * 
 * Explanation: The optimal
 * distribution of subsets is [1,2] and [1,4]. The incompatibility is (2-1) +
 * (4-1) = 4. Note that [1,1] and [2,4] would result in a smaller sum, but the
 * first subset contains 2 equal elements. 
 * 
 * Example 2:
 * 
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4 Output: 6 
 * 
 * Explanation: The optimal
 * distribution of subsets is [1,2], [2,3], [6,8], and [1,3]. The
 * incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 * 
 * 1 1 2 2 3 3 6 8
 * 
 * suboptimal solution got using naive approach:
 * seq   [1 2] [1 2] [3 6] [3 8]
 * range  1     1     3     5
 * 
 * ==================
 * suboptimal graph:
 * 
 * 12345678
 * oo
 * oo
 *   oooo
 *   oooooo
 * ===================
 *
 * optimal graph:
 * 
 * 12345678
 * oo
 * ooo
 *  oo
 *      ooo
 *
 * Difference: less overlaps 
 * 
 * Heuristics:
 * Use LIS-like approach: analyze from the tail
 * 
 * IDEA:
 * Note 1. chunks must be as narrow as possible
 * Note 2. equal nums are not allowed
 * Note 3. 
 * 
 * The narrowest chunks can be made from SORTED array
 * 
 * 
 */
public class Solution1681 {
	
	void dfs(int[] nums, int groupSize, int start, int end, int curSize, int sum, int used){
        if (curSize == groupSize){ // Current group is full, try to build next group
            sum += nums[end] - nums[start]; 
            if (sum >= min) return;
            for (int i = start + 1; i < nums.length; i++){ //Find new start index for next group
                int bit = 1 << i;
                if ((used & bit) == bit) continue;
                dfs(nums, groupSize, i, i, 1, sum, used | bit);
                return; //Important! Always pick the smallest available number, no need to continue the loop.
            }
            min = sum; //all numbers are used already, set min.
        }else{ // Current group is not full
            for (int newEnd = end + 1; newEnd < nums.length; newEnd++){ //Try to add more bigger number to the group
                int bit = 1 << newEnd;
                if ((used & bit) == bit || nums[newEnd] == nums[end]) continue;
                dfs(nums, groupSize, start, newEnd, curSize + 1, sum, used | bit);
            }
        }
    }
	
	 private int min = Integer.MAX_VALUE;
	    public int minimumIncompatibility(int[] nums, int k){
			int n = nums.length;
			if (n == 0) {
				return 0;
			}
			if (n % k != 0) {
				return -1;
			}

	        Arrays.sort(nums);
	        dfs(nums, n / k, 0, 0, 1, 0, 1);
	        return min == Integer.MAX_VALUE ? -1 : min;
	    }

	    

	
	

}
