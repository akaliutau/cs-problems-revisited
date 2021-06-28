package problem.greedy;

/**
 * 
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * Example:
 * 
 * Input: [2,3,1,1,4] Output: 2 
 * 
 * Explanation: The minimum number of jumps to
 * reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the
 * last index.
 * 
 * [2,3,1,1,4] 2: 
 * 0 -> 3 -> 4
 * 
 * [2,3,1,1,4]
 *  --->|       <-- need upgrade
 *    ----->|
 *    
 *  
 * IDEA:
 * use 2 vars:
 * 1) max radius of reachability maxReachablePos (update this on each step)
 * 2) maxPosInsideLastJump = radius of reachability achieved from last jump
 */
public class Solution45 {

	public int jump(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return 0;
		}
		// max position one could reach from 0
		int maxReachablePos = nums[0];
		// max number of 1-steps one could do
		// inside this jump
		int upperBoundaryForJump = maxReachablePos;

		int jumps = 1;
		for (int pos = 1; pos < n; ++pos) {
			// if to reach this point
			// one needs one more jump
			if (upperBoundaryForJump < pos) {
				++jumps;   // need one more jump
				upperBoundaryForJump = maxReachablePos; // upgrade upper boundary
			}
			// note, we have to update maxReachablePos after upperBoundaryForJump, as 
			maxReachablePos = Math.max(maxReachablePos, pos + nums[pos]);
		}
		return jumps;
	}


}
