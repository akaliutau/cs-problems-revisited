package problem.dp;

/**
 * 
 * Given an integer n, your task is to count how many strings of length n can be
 * formed under the following rules:
 * 
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u') Each vowel 'a'
 * may only be followed by an 'e'. Each vowel 'e' may only be followed by an 'a'
 * or an 'i'. Each vowel 'i' may not be followed by another 'i'. Each vowel 'o'
 * may only be followed by an 'i' or a 'u'. Each vowel 'u' may only be followed
 * by an 'a'. Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: n = 1 Output: 5 
 * 
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * 
 * IDEA:
 * 1. de-facto the same as factorial calculations
 * 2. start from the base case, namely from strings of length=1, and try to increase length, 
 *     using pre-calculated amount of strings at the previous steps
 *      
 *
 */
public class Solution1220 {

	public int countVowelPermutation(int n) {
		// each line i determine # of str of length i finished by the approp. letter
		long[] dp = new long[5];
		for (int i = 0; i < 5; i++) {// base case - strings of length=1
			dp[i] = 1;
		}
		
		for (int l = 1; l < n; l++) {
			long[] dpc = new long[5];
			dpc[0] = dp[1] + dp[2] + dp[4]; //calculate the # of a - it's determined by the # of e, i, u
			dpc[1] = dp[0] + dp[2]; // calculate the # of e
			dpc[2] = dp[1] + dp[3];
			dpc[3] = dp[2];
			dpc[4] = dp[2] + dp[3];
			for (int i = 0; i < 5; i++) {
				dp[i] = dpc[i] % 1_000_000_007;
			}
		}

		long sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += dp[i];
		}
		sum = sum % 1_000_000_007;
		return (int) sum;

	}

}
