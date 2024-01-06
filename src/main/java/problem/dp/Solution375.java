package problem.dp;

/**
 * We are playing the Guessing Game. The game will work as follows: 
 * I pick a number between 1 and n. You guess a number. 
 * If you guess the right number, you win the game. 
 * If you guess the wrong number, then I will tell you whether
 * the number I picked is higher or lower, and you will continue guessing. 
 * Every time you guess a wrong number x, you will pay x dollars. If you run out of
 * money, you lose the game. 
 * 
 * Given a particular n, return the minimum amount of
 * money you need to guarantee a win regardless of what number I pick.
 * 
 * IDEA:
 * the game easily can be reduced to subgames with the same structure
 * 
 * Base case:
 * [low, high=low]
 * return low as a cost
 * 
 * 1) find a global optimal solutions by rebuilding the whole tree of possibilities
 * 2) use cache to reduce complexity 
 * 
 */
public class Solution375 {
    
    int find(int low, int high, int[][] dp) {
        if (low >= high) {
            return 0;
        }
        if (dp[low][high] != 0) {// return cached value
            return dp[low][high];
        }
        int min =Integer.MAX_VALUE;
        
        for(int i = low; i <= high; i++) {
            int res = i + Math.max(find(low, i - 1, dp), find(i + 1, high, dp));// target equation
            min = Math.min(res, min);
        }
        dp[low][high] = min;
        return min;
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        return find(1, n, dp);
    }
    
    

}
