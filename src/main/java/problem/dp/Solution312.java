package problem.dp;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. 
 * If the you burst balloon       i 
 * you will get     nums[left] * nums[i] * nums[right] coins. 
 * 
 * Here left and right are adjacent indices of i. After the
 * burst, the left and right then becomes adjacent. Find the maximum coins you
 * can collect by bursting the balloons wisely. 
 * 
 * Example: Input: [3,1,5,8]
 * Output: 167 
 * 
 * Explanation: nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> [] 
 *             coins =  3*1*5      +  3*5*8  +   1*3*8  +  1*8*1  = 167
 *             
 *   0  1 2 3 4  5, n = 6
 *   ========================          
 *   1 [3,1,5,8] 1
 *            |     |  
 *          left   right
 *    
 *          [3],[1],[5],[8]  - solve starting from [1] blocks        
 *        [3,1],[1,5],[5,8]
 *         [3,1,5],[1,5,8]
 *            [3,1,5,8] 
 *    

 */
public class Solution312 {

    public int maxCoins(int[] nums) {

        int n = nums.length + 2;
        int[] newNums = new int[n];

        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }

        newNums[0] = newNums[n - 1] = 1; // (i-1) or (i+1) outside range

        
        int[][] dp = new int[n][n];// dp[i][j] = max money can get after to burst all balloons on [i,j]  

        // iterate over dp and incrementally build up to dp[0][n-1]
        for (int d = 2; d < n; d++) {
            for (int left = 0; left < n - d; left++) {
                int right = left + d;
                // check all baloons on [left,right]
                for (int i = left + 1; i < right; ++i) {
                    int canGetNow = newNums[left] * newNums[i] * newNums[right];
                    dp[left][right] = Math.max(dp[left][right],  dp[left][i] + canGetNow + dp[i][right]);
                }
            }
        }


        return dp[0][n - 1];
    }

}
