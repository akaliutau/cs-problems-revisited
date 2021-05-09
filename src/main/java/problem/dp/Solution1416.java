package problem.dp;

/**
 * A program was supposed to print an array of integers. The program forgot to
 * print whitespaces and the array is printed as a string of digits and all we
 * know is that all integers in the array were in the range [1, k] and there are
 * no leading zeros in the array.
 * 
 * Given the string s and the integer k. There can be multiple ways to restore
 * the array.
 * 
 * Return the number of possible array that can be printed as a string s using
 * the mentioned program.
 * 
 * The number of ways could be very large so return it modulo 10^9 + 7
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "1000", k = 10000 Output: 1 Explanation: The only possible array
 * is [1000] Example 2:
 * 
 * Input: s = "1000", k = 10 Output: 0 Explanation: There cannot be an array
 * that was printed this way and has all integer >= 1 and <= 10.
 * 
 * IDEA:
 * 
 */
public class Solution1416 {

	public int numberOfArrays(String s, int k) {
		int n = s.length();
		int mod = (int) 1e9 + 7;
		
		int[] dp = new int[n + 1]; // dp[i] - the number of arrays (respecting the problem's condition) until the index i of the string s
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			// trying all the integers up to the tenth digit (1 000 000 007 ) going backwards
			for (int j = i; j >= Math.max(1, i - 9); j--) {
				// if there are leading zeroes, continue
				if (s.charAt(j - 1) == '0') {
					continue;
				}
				// it needs to be casted as long be cause there are 10 digits numbers 
				long num = Long.parseLong(s.substring(j - 1, i));
				// if obeys the rules, then our current state is increased by answer of dp[j-1]
				if (num >= 1 && num <= k) {
					dp[i] = (dp[i] + dp[j - 1]) % mod;
				}
			}

		}
		return dp[n];
	}

}
