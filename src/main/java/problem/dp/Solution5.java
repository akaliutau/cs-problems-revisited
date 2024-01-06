package problem.dp;

/**
 * Given a string s, return the longest palindromic substring in s.
 * 
 * vabbaa
 *   ||
 *   lr 
 * 
 * IDEA:
 * expands from center, considering each i as a center
 * 
 */
public class Solution5 {

    // run away from center until boundary || char change
    int expandAroundCenter(char[] s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int n = s.length();
        int start = 0, end = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

 
 
}
