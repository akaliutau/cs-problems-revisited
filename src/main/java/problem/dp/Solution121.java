package problem.dp;

/**
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * 
 * Input: [7,1,5,3,6,4] Output: 5 
 * Explanation: Buy on day 2 (price = 1) and sell
 * on day 5 (price = 6), profit = 6-1 = 5. Not 7-1 = 6, as selling price needs
 * to be larger than buying price.
 * 
 * IDEA:
 * max profit at any time = cur_price  - min price seen so far
 * 
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



}
