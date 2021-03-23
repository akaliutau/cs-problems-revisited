package com.problems.dp;

import java.util.Arrays;

/**
 * Given an integer array arr, and an integer target, return the number of
 * tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 * 
 * As the answer can be very large, return it modulo 109 + 7.
 * 
 * Example 1:
 * 
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8 Output: 20
 * 
 * Explanation: Enumerating by the values (arr[i], arr[j], arr[k]):
 * 
 * (1, 2, 5) occurs 8 times;
 * 
 * (1, 3, 4) occurs 8 times;
 * 
 * (2, 2, 4) occurs 2 times;
 * 
 * (2, 3, 3) occurs 2 times.
 * 
 * IDEA:
 * 1) investigate all possibilities of [left, center, right] pairs
 * 1,1,2,2,3,3,4,4,5,5
 * |     |     |
 * left center  right
 * 
 * 
 */
public class Solution923 {

	static long MOD = 1_000_000_007;

	public int threeSumMulti(int[] arr, int target) {
		long ans = 0;
		Arrays.sort(arr);
		int n = arr.length;

		for (int i = 0; i < n; ++i) {
			int tgt = target - arr[i];
			int center = i + 1;
			int k = n - 1;

			while (center < k) {
				if (arr[center] + arr[k] < tgt) {
					center++;
				} else if (arr[center] + arr[k] > tgt) {
					k--;
				} else if (arr[center] != arr[k]) {// find (i, j, k) 
					int left = 1;
					int right = 1;
					while (center + 1 < k && arr[center] == arr[center + 1]) {
						left++;
						center++;
					}
					while (k - 1 > center && arr[k] == arr[k - 1]) {
						right++;
						k--;
					}

					ans += left * right;
					ans %= MOD;
					center++;
					k--;
				} else {
					int m = k - center + 1;
					ans += m * (m - 1) / 2;
					ans %= MOD;
					break;
				}
			}
		}

		return (int) ans;
	}
}
