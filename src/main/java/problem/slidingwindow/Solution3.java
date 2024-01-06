package problem.slidingwindow;

import java.util.Arrays;

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
 * consider the block [i, j] without without repeating characters - 
 * look at the array from point j
 * 
 * 
 * 
 */
public class Solution3 {

	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int ans = 0;
		int[] index = new int[128]; // last seen index of character
		Arrays.fill(index, -1);
		
		char[] letters = s.toCharArray();
		
		// try to extend the range [i, j]
		int leftmost = 0;// earliest possible start = MAX {indices of all letters in block}
		
		for (int j = 0; j < n; j++) {
			int curLetter = letters[j];
			int lastSeenAt = index[curLetter];// either -1 or position of letter in the past
		    leftmost = Math.max(lastSeenAt + 1, leftmost);
		    int len = j - leftmost + 1;
			ans = Math.max(ans, len);
			index[curLetter] = j;
		}
		return ans;
	}

	

}
