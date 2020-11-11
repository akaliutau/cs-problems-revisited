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
 * IDEA:
 * consider the block [i, j] without without repeating characters
 * look at the array from point j
 * 
 */
public class Solution3 {

	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int ans = 0;
		int[] index = new int[128]; // current index of character
		char[] letters = s.toCharArray();
		// try to extend the range [i, j]
		int lowestPossible = 0;// earliest possible start = MAX {indecies of all letters in block}
		
		for (int j = 0; j < n; j++) {
		    lowestPossible = Math.max(index[letters[j]], lowestPossible);
			ans = Math.max(ans, j - lowestPossible + 1);
			index[letters[j]] = j + 1;
		}
		return ans;
	}

	

}
