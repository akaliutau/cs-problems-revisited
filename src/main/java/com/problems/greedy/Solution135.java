package com.problems.greedy;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating
 * value.
 * 
 * You are giving candies to these children subjected to the following
 * requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get
 * more candies than their neighbors. What is the minimum candies you must give?
 * 
 * Example 1:
 * 
 * Input: [1,0,2] Output: 5 Explanation: You can allocate to the first, second
 * and third child with 2, 1, 2 candies respectively.
 * 
 * 
 */
public class Solution135 {

	public int candy(int[] ratings) {
		int sum = 0;
		int n = ratings.length;
		int[] leftNei = new int[n];
		int[] rightNei = new int[n];
		Arrays.fill(leftNei, 1);// because everyone must get at least 1
		Arrays.fill(rightNei, 1);
		
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				leftNei[i] = leftNei[i - 1] + 1;
			}
		}
		for (int i = n - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				rightNei[i] = rightNei[i + 1] + 1;
			}
		}
		for (int i = 0; i < n; i++) {
			sum += Math.max(leftNei[i], rightNei[i]);
		}
		return sum;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
