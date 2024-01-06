package problem.dp;

/**
 * Given an array of non-negative integers nums, you are initially positioned at
 * the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4] Output: true 
 * 
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * IDEA:
 * during traversing through all elems of the array, re-calc and maintain the
 * numsCoveredUpperBoundary - the most distant element which can be reached using jumps seen so far
 * 
 * 
 * O(n*k)
 */
public class Solution55 {

	public boolean canJump(int[] nums) {
		int n = nums.length;
		int numsCoveredUpperBoundary = 0;
		if (n == 0) {
			return false;
		}
		if (n == 1) {
			return nums[0] >= 0;
		} else if (nums[0] == 0) {
			return false;
		}

		boolean[] coverMap = new boolean[n];
		for (int i = 0; i < n - 1; i++) {
			int dist = nums[i];
			if (dist == 0) {// check wall presence
				if (numsCoveredUpperBoundary <= i) {
					return false;
				}
			}
			for (int j = i; j <= i + dist; j++) {
				if (j > -1 && j < n) {
					if (j == n - 1) {
						return true;
					}
					coverMap[j] = true;
					if (numsCoveredUpperBoundary < j) {
						numsCoveredUpperBoundary = j;
					}
				}
			}
		}
		return coverMap[n - 1];

	}

}
