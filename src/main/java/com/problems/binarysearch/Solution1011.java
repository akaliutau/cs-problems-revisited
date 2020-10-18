package com.problems.binarysearch;

/**
 * A conveyor belt has packages that must be shipped from one port to another
 * within D days.
 * 
 * The i-th package on the conveyor belt has a weight of weights[i]. Each day,
 * we load the ship with packages on the conveyor belt (in the order given by
 * weights). We may not load more weight than the maximum weight capacity of the
 * ship.
 * 
 * Return the least weight capacity of the ship that will result in all the
 * packages on the conveyor belt being shipped within D days.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], D = 5 Output: 15 Explanation: A ship
 * capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5 2nd day: 6, 7 3rd day: 8 4th day: 9 5th day: 10
 * 
 * Note that the cargo must be shipped in the order given, so using a ship of
 * capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6,
 * 7), (8), (9), (10) is not allowed
 * 
 * 
 */
public class Solution1011 {

	boolean isPossible(int[] weights, int D, int k) {
		int d = 1;
		int sum = 0;
		for (int weight : weights) {
			if (sum + weight > k) {
				sum = 0;
				d++;
			}
			sum += weight;
		}
		return d <= D;
	}

	public int shipWithinDays(int[] weights, int d) {
		int right = 0, left = 0;
		for (int weight : weights) {
			right += weight;
			left = Math.max(left, weight);
		}
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (!isPossible(weights, d, mid)) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
