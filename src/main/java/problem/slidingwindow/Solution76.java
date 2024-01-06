package problem.slidingwindow;

/**
 * 
 * Sliding window
 * 
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * Input: S = "ABAACBAB" T = "ABC" 
 * Output: "ACB"
 * 
 * IDEA:
 * 
 * Flow:
 * 
 * 1) init state
 * |
 * ABAACBAB
 * |
 * 
 * 2) the first desirable window
 * |
 * ABAACBAB
 *     |
 * 
 * 3) still desirable
 *  |
 * ABAACBAB
 *     |
 * 
 * 4) no more desirable
 *   |
 * ABAACBAB
 *     |
 * 
 * 5) desirable
 *   |
 * ABAACBAB
 *      |
 * 
 * 6) answer (repeating will not improve this result)
 *    |
 * ABAACBAB
 *      |
 * 
 * 
 */
public class Solution76 {
	
	static class Result {
		int len = 0;
		int l = 0, r = 0;
	}
	
	public String minWindow(String s, String t) {

		if (s.length() == 0 || t.length() == 0) {
			return "";
		}

		// Dictionary which keeps a count of all the unique characters in t.
		int[] have = new int[256];
		for (int i = 0; i < t.length(); i++) {
			have[t.charAt(i)] ++;
		}
		int required = 0;
		for (int i = 0; i < 256; i++) {
			if (have[i] > 0) {
				required ++;
			}
		}

		int l = 0, r = 0;

		// common is used to keep track of how many unique characters in t
		// are present in the current window in its desired frequency.
		// e.g. if t is "AABC" then the window must have two A's, one B and one C.
		// Thus common would be = 3 when all these conditions are met.
		int common = 0;

		// count of all the unique characters in the current window.
		int[] windowCounts = new int[256];

		Result ans = new Result();

		while (r < s.length()) {
			char c = s.charAt(r);
			windowCounts[c] ++;

			if (have[c] > 0 && windowCounts[c] == have[c]) {// update this if have is a SET - limit to 0s & 1s
				common++;
			}

			while (l <= r && common == required) {
				c = s.charAt(l);
				// update stat
				if (ans.len == 0 || ans.len > r - l + 1) {// found smaller or first time
					ans.len = r - l + 1;
					ans.l = l;
					ans.r = r;
				}

				windowCounts[c] --;
				if (have[c] > 0 && windowCounts[c] < have[c]) {// if removed char was in have set
					common--;
				}
				l++;
			}
			r++;
		}

		return ans.len == 0 ? "" : s.substring(ans.l, ans.r + 1);
	}



}
