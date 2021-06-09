package problem.priorityqueue;

import java.util.PriorityQueue;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 * 
 * You are initially standing at index 0. In one move, you can jump at most k
 * steps forward without going outside the boundaries of the array. That is, you
 * can jump from index i to any index in the range [i + 1, min(n - 1, i + k)]
 * inclusive.
 * 
 * You want to reach the last index of the array (index n - 1). Your score is
 * the sum of all nums[j] for each index j you visited in the array.
 * 
 * Return the maximum score you can get.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,-1,-2,4,-7,3], k = 2 Output: 7 
 * 
 * Explanation: You can choose
 * your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is
 * 7.
 * 
 * IDEA:
 * 
 * 
 *
 */
public class Solution1696 {
	public int maxResult(int[] nums, int k) {
		int n = nums.length;
		int[] score = new int[n];
		score[0] = nums[0];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o, p) -> p[0] - o[0]);
		pq.add(new int[] { nums[0], 0 });
		for (int i = 1; i < n; i++) {
			while (pq.peek()[1] < i - k) {
				pq.remove();
			}
			score[i] = nums[i] + score[pq.peek()[1]];
			pq.add(new int[] { score[i], i });
		}
		return score[n - 1];
	}
}
