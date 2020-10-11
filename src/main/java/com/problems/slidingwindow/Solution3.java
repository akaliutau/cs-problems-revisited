package com.problems.slidingwindow;

/**
 * 
 *  Sliding window
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
