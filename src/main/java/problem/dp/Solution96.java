package problem.dp;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store
 * values 1 ... n? Example: Input: 3 Output: 5 Explanation: Given n = 3, there
 * are a total of 5 unique BST's:
 * 
 *      1
 *      
 *      1         2
 *       \       /
 *        2     1
 * 
 *      1      3       3      2    1 
 *       \    /       /      / \    \ 
 *        3  2       1      1   3    2
 *       /  /         \               \ 
 *      2  1           2               3 
 *      
 *      Constraints: 1 <= n <= 19
 */
public class Solution96 {
    
    public int numTrees(int n) {
        // dp[i] - the number of unique BST for a sequence of length i (NOTE: content is irrelevant!)
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        // [1, 2, 3, ...,  i-1] [i] [i+1, ..., n]
        // f.e., to construct an unique BST out of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, 
        // we need to construct a subtree out of its left subsequence [1, 2] and another subtree out of the right subsequence [4, 5, 6, 7],
        // and then combine them together (i.e. cartesian product)
        for (int i = 2; i <= n; ++i) {// for all possible roots
           // dp[0]*d[i-1] + dp[1]*dp[i-2] + ... + dp[i-1] * dp[0] // dp[0] - number of BST of length 0
           for (int j = 1; j <= i; ++j) {
               dp[i] += dp[j - 1] * dp[i - j];
           }
        }
        return dp[n];
      }


}
