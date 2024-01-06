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
 * 1. use DFS to build the chained buskets
 * 2. use drop rules to cut-off wrong scenarios
 * 
 * 1 1 2 2 3 3 6 8
 * |   |
 * start from[1,1] to [1,2] then [1,3], [1,6], [1,8]
 * after forming one of these 4 groups continue the process - 
 * try to form the next group, starting at [1,1] - 2nd one, and so on
 * 
 */
public class Solution1681 {
	
	
	void dfs(int[] nums, int busketSize, int left, int right, int curSize, int sum, int used){
        if (curSize == busketSize){ // Current group is full, try to build next group
            sum += nums[right] - nums[left]; 
            if (sum >= min) return;// drop wrong scenarios - this optimization is possible because the sequence is strictly growing
            
            for (int i = left + 1; i < nums.length; i++){ //Find new left index for next group
                int bit = 1 << i;
                if ((used & bit) == bit) continue;
                dfs(nums, busketSize, i, i, 1, sum, used | bit);
                return; //Important! Always pick the smallest available number, no need to continue the loop.
            }
            min = sum; //all numbers are used already, set min.
            
        }else{ // Current group is not full
        	// start from basket [0,0], then try to extend right boundary
            for (int newRight = right + 1; newRight < nums.length; newRight++){ //Try to add bigger number to the group
                int bit = 1 << newRight;
                if ((used & bit) == bit || nums[newRight] == nums[right]) continue;// drop already used numbers OR equal ones
                dfs(nums, busketSize, left, newRight, curSize + 1, sum, used | bit);
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
        int busketSize = n / k; 
        dfs(nums, busketSize, 0, 0, 1, 0, 1);
        
        return min == Integer.MAX_VALUE ? -1 : min;
	}

	    

	
	

}
