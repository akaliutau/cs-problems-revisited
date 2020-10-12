package com.problems.slidingwindow;

import java.util.HashMap;
import java.util.Map;

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

	public String minWindow(String s, String t) {

		if (s.length() == 0 || t.length() == 0) {
			return "";
		}

		// Dictionary which keeps a count of all the unique characters in t.
		Map<Character, Integer> have = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			int count = have.getOrDefault(t.charAt(i), 0);
			have.put(t.charAt(i), count + 1);
		}

		int required = have.size();

		int l = 0, r = 0;

		// formed is used to keep track of how many unique characters in t
		// are present in the current window in its desired frequency.
		// e.g. if t is "AABC" then the window must have two A's, one B and one C.
		// Thus formed would be = 3 when all these conditions are met.
		int formed = 0;

		// count of all the unique characters in the current window.
		Map<Character, Integer> windowCounts = new HashMap<>();

		int[] ans = { -1, 0, 0 };

		while (r < s.length()) {
			char c = s.charAt(r);
			int count = windowCounts.getOrDefault(c, 0);
			windowCounts.put(c, count + 1);

			if (have.containsKey(c) && windowCounts.get(c).intValue() == have.get(c).intValue()) {
				formed++;
			}

			while (l <= r && formed == required) {
				c = s.charAt(l);
				if (ans[0] == -1 || r - l + 1 < ans[0]) {
					ans[0] = r - l + 1;
					ans[1] = l;
					ans[2] = r;
				}

				windowCounts.put(c, windowCounts.get(c) - 1);
				if (have.containsKey(c) && windowCounts.get(c).intValue() < have.get(c).intValue()) {
					formed--;
				}
				l++;
			}
			r++;
		}

		return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
