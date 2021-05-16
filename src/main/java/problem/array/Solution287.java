package problem.array;

/**
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive. There is only one duplicate number in nums,
 * return this duplicate number. Follow-ups: How can we prove that at least one
 * duplicate number must exist in nums? Can you solve the problem without
 * modifying the array nums? Can you solve the problem using only constant, O(1)
 * extra space? Can you solve the problem with runtime complexity less than
 * O(n2)? 
 * 
 * Example 1: Input: nums = [1,3,4,2,2] Output: 2 
 * 
 * 
 * Example 2: Input: nums = [3,1,3,4,2] Output: 3
 * 
 * IDEA: 
 * 
 * sum = n * (n + 1) /2
 * 
 * sum + k = actualSum, =>
 * k = actualSum - n
 * 
 */
public class Solution287 {

    public int findDuplicate(int[] nums) {

    	int n = nums.length + 1;
    	long sum = 0;
    	for (int num : nums) {
    		sum += num;
    	}

        return (int) (sum - n * (n + 1) / 2);

    }

}
