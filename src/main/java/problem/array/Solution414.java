package problem.array;

/**
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n). 
 * 
 * Example 1: Input: [3, 2, 1] Output: 1 Explanation: The third
 * maximum is 1.
 * 
 * IDEA:
 * 1) introduce a limit for max function (ie. max element with additional constraint) - to find the 2nd biggest
 * 2) repeat to find the 3rd, 4th, etc
 */
public class Solution414 {
    
	// returns the biggest number with limit set to ceil
    static long find(int[] nums, long ceil) {
        long maxVal = Long.MIN_VALUE;
        for (int num : nums) {
            if (maxVal < num && num < ceil) {
                maxVal = num;
            }
        }
        return maxVal;
    }

    public int thirdMax(int[] nums) {
        long first = find(nums, Long.MAX_VALUE);
        long second = find(nums, first);
        if (second == Long.MIN_VALUE) {
            return (int)first;
        }
        long third = find(nums, second);
        if (third == Long.MIN_VALUE) {
            return (int)first;
        }
        return (int)third;
    }

}
