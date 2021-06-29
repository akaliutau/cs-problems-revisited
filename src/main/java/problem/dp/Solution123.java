package problem.dp;

/** 
 * 
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * 
 *
 *    4 4 4 4 3 3 0               
 *    | |
 * [3,3,5,0,0,3,1,4]
 *          |     |
 *  0 0 2 2 2 3 3 4     
 *  
 *  IDEA:
 *  1. divide the array on 2 parts and compute the best transaction on each range, 
 *  f.e.
 *  [3,3,5,0,0,3,1,4]
 *         |
 *  find      find
 *  best      trans on this range 
 *  trans
 *          
 *  to achieve this we have to calc a structure which will contain the best transaction
 *  on [0, i]  and on [i, n-1] 
 *          
 */
public class Solution123 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        int leftMin = prices[0];
        int rightMax = prices[n - 1];

        int[] firstTransProfit = new int[n];
        // fill out the right DP array with an additional zeros for convenience.
        int[] secondTransProfit = new int[n + 1];

        // profit on the 1st part
        for (int l = 1; l < n; ++l) {
            firstTransProfit[l] = Math.max(firstTransProfit[l - 1], prices[l] - leftMin);
            leftMin = Math.min(leftMin, prices[l]);
        }
        
        // profit on the 2nd part
        for (int r = n - 2; r > -1; r--) {
            secondTransProfit[r] = Math.max(secondTransProfit[r + 1], rightMax - prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
        }

        int maxProfit = 0;
        for (int i = 0; i < n; ++i) {
            maxProfit = Math.max(maxProfit, firstTransProfit[i] + secondTransProfit[i + 1]);
        }
        return maxProfit;
    }

   

}
