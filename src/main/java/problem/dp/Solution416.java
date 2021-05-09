package problem.dp;

/**
 * Given a non-empty array nums containing only positive integers, find if the
 * array can be partitioned into two subsets such that the sum of elements in
 * both subsets is equal. 
 * 
 * Example 1: Input: nums = [1,5,11,5] Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * total = 22 % 2 ? yes
 * dp\sum     | 0   1   2   3   4   5   6  7  8  9  10  11    
 * {}         | t   f   f   f   f   f   f  f  f  f  f   f   
 * {1}        | 0={}|1, 1 = {}|1,  2 != {}|1, and so on  
 * {1,5}      |
 * {1,5,11}   |
 * {1,5,11,5} |
 * 
 *  /|\
 *   |
 * sub-problems, full reference will be (i:sum) 
 * 
 *                       
 *                 have so far:[1,5,11,5] othersum=11
 *                     /                \       
 *              have:[1,5,11]sum=11-5    [1,5,11] sum=11     
 *                  case #2                  case #1
 *          included 5 into other sum   included 5 into this sum, curSum += 5
 *          
 *  return true, if sum(have) = sum (usually if 1 or 0 elems in have set)        
 */
public class Solution416 {

    public boolean canPartition(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0)
            return false;
        int subSum = total / 2;
        int n = nums.length;
        // dp[i][j]=true if the sum j can be formed by array elements in subset 0..i
        // otherwise dp[i][j] = false
        boolean dp[][] = new boolean[n + 1][subSum + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            int curr = nums[i - 1];
            for (int sum = 0; sum <= subSum; sum++) {
                if (sum < curr) {// curr elem is too big to be included in set
                    dp[i][sum] = dp[i - 1][sum];
                } else {// make choice: 1) choose subset without this elem OR 2) pick up answer for sum without this elem, ref = sum - curr
                    int ref = sum - curr;
                    dp[i][sum] = dp[i - 1][sum] || dp[i - 1][ref];
                }
            }
        }
        return dp[n][subSum];
    }

}
