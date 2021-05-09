package problem.dp;

/**
 * 
 * DP
 * 
 * 
 * The value of balance[d][t][s] represents the best profit (i.e. balance) we can have at the end of
 * the d-th day, with t remaining transactions to make and s stocks.
 * 
 * The next step is finding out the so-called "transition equation", which is a
 * method that tells you how to jump from one state to another.
 * 
 * We start with balance[0][0][0] = 0 and balance[0][0][1]=-prices[0], and our final aim
 * is max of balance[n-1][t][0] from t=0 to t=k. Now, we need to fill out the entire
 * array to find out the result. Assume we have gotten the results before day d,
 * and we need to calculate the profit of day d. There are only four possible
 * actions we can do on day d: 
 * 
 * 1. keep holding the stock, 
 * 2. keep not holding the stock, 
 * 3. buy the stock, or 
 * 4. sell the stock. 
 * 
 * The profit is easy to calculate.
 * 
 * Keep holding the stock: balance[d][t][1] = balance[d-1][t][1]
 * 
 * Keep not holding the stock: balance[d][t][0] = balance[d-1][t][0]
 * 
 * Buying, when t > 0: balance[d][t][1] = balance[d-1][t-1][0] - prices[d]
 * 
 * Selling: balance[d][t][0] = balance[d-1][t][1] + prices[d]
 * 
 * We can combine they together to find the maximum profit:
 * 
 * balance[d][t][1] = max(balance[d-1][t][1], balance[d-1][t-1][0] - prices[d]) // update for "hold" stock scenario
 * balance[d][t][0] = max(balance[d-1][t][0], balance[d-1][t][1] + prices[d])   // update for "not hold" stock scenario
 * 
 * 
 */
public class Solution188 {

	public int maxProfit(int k, int[] prices) {
		int n = prices.length;

		// solve edge cases
		if (n <= 0 || k <= 0) {
			return 0;
		}

		if (2 * k > n) {// we can do 1 transaction each 2 days
			int res = 0;
			for (int d = 1; d < n; d++) {
				res += Math.max(0, prices[d] - prices[d - 1]);
			}
			return res;
		}

		int[][][] balance = new int[n][k + 1][2];

		// initialize the array with -inf
		// we use -1e9 here to prevent overflow
		for (int d = 0; d < n; d++) {
			for (int t = 0; t <= k; t++) {
				balance[d][t][0] = -1000000000;
				balance[d][t][1] = -1000000000;
			}
		}

		// set starting value
		balance[0][0][0] = 0;
		balance[0][1][1] = -prices[0];

		// fill the array
		for (int d = 1; d < n; d++) {
			for (int t = 0; t <= k; t++) {
				// transition equation - can hold status quo or sell stock
				balance[d][t][0] = Math.max(balance[d - 1][t][0], balance[d - 1][t][1] + prices[d]);
				// can hold status quo or buy stock
				if (t > 0) {
					balance[d][t][1] = Math.max(balance[d - 1][t][1], balance[d - 1][t - 1][0] - prices[d]);
				}
			}
		}

		int res = 0;
		for (int t = 0; t <= k; t++) {
			res = Math.max(res, balance[n - 1][t][0]);// choose max transaction
		}

		return res;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
