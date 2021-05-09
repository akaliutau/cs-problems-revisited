package problem.dp;

/**
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest runningSum and return its runningSum.
 * 
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4] Output: 6
 * Explanation: [4,-1,2,1] has the largest runningSum = 6.
 */
public class Solution53 {

    public int maxSubArray(int[] nums) {

        int runningSum = nums[0];// runningSum of all elems left to index = right
        int bestSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (runningSum >= 0) {// does not make sense to add num < 0
                runningSum += nums[i];
            } else {
                runningSum = nums[i];
                if (runningSum > bestSum) {// reset left boundary
                    
                }
            }
            if (runningSum > bestSum) {
                bestSum = runningSum;
            }
        }
        return bestSum;

    }


}
