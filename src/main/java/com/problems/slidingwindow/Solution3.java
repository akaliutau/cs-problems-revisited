package com.problems.slidingwindow;

/**
 * 
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * Example 1:
 * 
 * Input: s = "abcabcbb" Output: 3 Explanation: The answer is "abc", with the
 * length of 3.
 * 
 */
public class Solution3 {

	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int ans = 0;
		int[] index = new int[128]; // current index of character
		char[] letters = s.toCharArray();
		// try to extend the range [i, j]
		int i = 0;
		for (int j = 0; j < n; j++) {

			i = Math.max(index[letters[j]], i);
			ans = Math.max(ans, j - i + 1);
			index[letters[j]] = j + 1;
		}
		return ans;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
