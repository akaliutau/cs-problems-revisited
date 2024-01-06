package problem.dp;

import java.util.Arrays;

/**
 * 
 * Find max profit from multiple transactions
 * 
 * 
 * The value of balance[d][t][s] represents the best profit (i.e. balance) we can have at the end of
 * the d-th day, with t remaining transactions to make and s stocks.
 * 
 * The next step is finding out the so-called "transition equation", which is a
 * method that tells you how to jump from one state to another.
 * 
 * We start with 
 * balance[0][0][0] = 0           <---  have no stock, balance 0
 * and 
 * balance[0][0][1]=-prices[0],   <---  bought stock at level -prices[0]
 * 
 * and our final aim
 * is max of balance[n-1][t][0] from t=0 to t=k. Now, we need to fill out the entire
 * array to find out the result. Assume we have gotten the results before day d,
 * and we need to calculate the profit of day d. There are only four possible
 * actions we can do on day d: 
 * 
 * 1. keep holding the stock, 
 * 3. buy the stock, or 
 * 4. sell the stock. 
 * 
 * The profit is easy to calculate.
 * 
 * Keep holding the stock: balance[d][t][1] = balance[d-1][t][1]
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
 * O(nk)
 */
public class Solution188 {

    int memo[][][];
    
    int solve(int n, int idx, int k, int hasActiveTransaction, int[] prices) {
		//base case 
        if(k == 0 || idx == n)
            return 0;
        if(memo[hasActiveTransaction][idx][k] != -1) {
            return memo[hasActiveTransaction][idx][k];
        }
        int res = -1;
        if(hasActiveTransaction == 1) {
			int sell = solve(n, idx+1, k-1, 0, prices) + prices[idx]; // closed transaction, get profit
			int hold = solve(n, idx+1, k,   1, prices); //skip current opportunity, keep transaction, advance index
            res = Math.max(sell, hold);
        }else {
			int buy =  solve(n, idx+1, k, 1, prices) - prices[idx]; // opened transaction, correct the profit to the base price
			int skip = solve(n, idx+1, k, 0, prices); //skip current opportunity, keep transaction closed, advance index
            res = Math.max(buy, skip);
        }
        memo[hasActiveTransaction][idx][k] = res;
        return res;
    }
    
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        memo = new int[2][n][k+1];
        if(n<=1 || k==0)
            return 0;
        for(int[][] x:memo)
            for(int[] y:x)
                Arrays.fill(y, -1);
        
        return solve(n, 0, k, 0, prices);
    }

}
