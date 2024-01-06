package problem.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so
 * that T is a subsequence of W.
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "". If there are multiple such minimum-length windows, return
 * the one with the left-most starting index.
 * 
 * Example 1:
 * 
 * Input: S = "abcdebdde", T = "bde" Output: "bcde" Explanation: "bcde" is the
 * answer because it occurs before "bdde" which has the same length. "deb" is
 * not a smaller window because the elements of T in the window must occur in
 * order
 * 
 * IDEA:
 * 
 * 
 * 
 */
public class Solution727 {

	public String minWindow(String s, String t) {
		int n = s.length();
		int m = t.length();
		int[] last = new int[26];
		int[][] dp = new int[n][26];// distribution of index of last seen index for each letter on the string s[0..i] 
		Arrays.fill(last, -1);

		for (int i = n - 1; i >= 0; --i) {
			last[s.charAt(i) - 'a'] = i;
			for (int k = 0; k < 26; ++k) {// copy vector
				dp[i][k] = last[k];
			}
		}

		List<int[]> windows = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			if (s.charAt(i) == t.charAt(0))
				// generate possible windows, by coincidence of the first letter 
				// i.e. a[b]cde[b]dde <-> [b]de
				windows.add(new int[] { i, i });
		}
		
		for (int j = 1; j < m; ++j) {// for all consequent letters in t with index letter
			int letter = t.charAt(j) - 'a';
			for (int[] window : windows) {
			   //1) not the last (must be subseq)   2) have such letter in s
				if (window[1] < n - 1 && dp[window[1] + 1][letter] >= 0) {
					window[1] = dp[window[1] + 1][letter];
				} else {
					window[0] = window[1] = -1;// reset
					break;
				}
			}
		}

		int[] ans = { -1, n };
		for (int[] window : windows) {
			if (window[0] == -1)
				break;
			if (window[1] - window[0] < ans[1] - ans[0]) {// choose min
				ans = window;
			}

		}
		return ans[0] >= 0 ? s.substring(ans[0], ans[1] + 1) : "";
	}

}
