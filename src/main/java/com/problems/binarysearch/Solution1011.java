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
 * 1st day: 1, 2, 3, 4, 5 
 * 2nd day: 6, 7 
 * 3rd day: 8 
 * 4th day: 9 
 * 5th day: 10
 * 
 * Note that the cargo must be shipped in the order given, so using a ship of
 * capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6,
 * 7), (8), (9), (10) is not allowed
 * 
 * IDEA:
 * 1) calc the boundaries for capacity of the ship
 * 2) use binary search to find the golden point
 * 
 * NOTE: the binary search is possible due to the linear nature of tonnage function
 * 
 *             [0,3]
 *          /        \
 *       [0,1]          [1,3]
 *      /    \
 *    [0,0]   [0,1]  <--- needed to add 1 to avoid infinite loop - see the code 
 */
public class Solution1011 {

	// simulate the shipping scenario
	// this is a costly function, so must be invoked as rare as possible
	boolean isPossible(int[] weights, int D, int k) {
		int d = 1;
		int sum = 0;
		for (int weight : weights) {
			if (sum + weight > k) {// if overweight, close the previous sum and inc the day counter
				sum = 0;
				d++;
			}
			sum += weight;
		}
		return d <= D;
	}

	public int shipWithinDays(int[] weights, int d) {
		// boundaries for capacity of the ship
		int maxTonnage = 0, minTonnage = 0;
		for (int weight : weights) {// calculate boundaries
			maxTonnage += weight;
			minTonnage = Math.max(minTonnage, weight);
		}
		// search for the ideal balanced solution using linear function
		while (minTonnage < maxTonnage) {
			int mid = minTonnage + (maxTonnage - minTonnage) / 2;
			if (!isPossible(weights, d, mid)) {
				minTonnage = mid + 1;// need to add 1 to avoid infinite loop
			} else {
				maxTonnage = mid;
			}
		}
		return minTonnage;
	}


}
