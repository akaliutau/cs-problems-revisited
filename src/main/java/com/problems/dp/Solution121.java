package com.problems.dp;

/**
 * 
 * DP
 */
public class Solution121 {

	public int maxProfit(int[] prices) {
		int n = prices.length;
		if (n == 0) {
			return 0;
		}
		int bestMin = prices[0];
		int bestMax = prices[0];
		int profit = 0;
		for (int price : prices) {
			if (price < bestMin) {// reset on better min
				bestMin = price;
				bestMax = price;
			}
			bestMax = Math.max(bestMax, price);// always update max
			profit = Math.max(profit, bestMax - bestMin);
		}
		return profit;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
