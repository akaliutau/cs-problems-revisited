package problem.dp;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product. 
 * 
 * Example 1:
 * Input: [2,3,-2,4] Output: 6 
 * Explanation: [2,3] has the largest product 6
 * 
 *  there are 2 possible ways to construct the biggest prod:
 *  n * biggest positive prod so far
 *  -n * smallest negative prod so far   
 *  
 */
public class Solution152 {

    public int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = Math.max(maxProd, minProd);

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(maxProd * curr, minProd * curr));
            minProd = Math.min(curr, Math.min(maxProd * curr, minProd * curr));

            maxProd = temp_max;

            result = Math.max(maxProd, result);
        }

        return result;
    }

}
