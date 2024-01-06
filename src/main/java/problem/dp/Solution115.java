package problem.dp;

/**
 * Given two strings s and t, return the number of distinct subsequences of s
 * which equals t. A string's subsequence is a new string formed from the
 * original string by deleting some (can be none) of the characters without
 * disturbing the relative positions of the remaining characters. (i.e., "ACE"
 * is a subsequence of "ABCDE" while "AEC" is not). It's guaranteed the answer
 * fits on a 32-bit signed integer. 
 * 
 * Example 1: Input: s = "rabbbit", t = "rabbit" Output: 3 
 * 
 * Explanation: As shown below, there are 3 ways you can
 * generate "rabbit" from S.
 * ____ __ 
 * rabbbit 
 * 
 * __ ____
 * rabbbit 
 * 
 * ___ ___
 * rabbbit
 * 
 * IDEA:
 * lets t=ra, s=ram
 * 
 * if we have a sequence
 * [ra]
 * number of distinct subsequences, layer by layer:
 * 
 * i=0, t="",   [""]  ["r"]  ["ra"]  ["ram"]
 *                  \
 * i=1, t="r",  [""]<-["r"]<-["r"]<- ["r"]
 *     
 * i=2, t="ra", [""]<-["r"]<-["r"]<- ["r"]
 * 
 * where
 * \ = use data from previous iteration, like this
 * 
 * ["", r, a, ra] = [{"",r} + {"",r} * a]
 * 
 * <- = copy prev result-set
 *  
 * ["", r, a, ra] --> ["", r, a, ra]
 * 
 * 
 */
public class Solution115 {

    public int numDistinct(String s, String t) {
        
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int l1 = str1.length;
        int l2 = str2.length;
         
        int[][] dp = new int[l2+1][l1+1];// dp[i][j] - the answer for strings with len = i and len = j
        
        for (int i = 0; i < l1; i++) {
            dp[0][i] = 1; //for empty string t, any s can only have one distinct subsequence - to delete everything in s to get t
        }
        for (int i = 1; i <= l2; i++) {
            for (int j = 1; j <= l1; j++) {
                if (str2[i-1] == str1[j-1]) { // choose t's every character 
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }else {// the answer is the same as for string s of length=l-1, copy that
                    dp[i][j] = dp[i][j - 1];
                }
            }            
        }
        
        return dp[l2][l1];

    }

}
