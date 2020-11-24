package com.problems.string;

/**
 * Given two strings s and t, find the number of ways you can choose a non-empty
 * substring of s and replace a single character by a different character such
 * that the resulting substring is a substring of t. In other words, find the
 * number of substrings in s that differ from some substring in t by exactly one
 * character. For example, the underlined substrings in "computer" and
 * "computation" only differ by the 'e'/'a', so this is a valid way. Return the
 * number of substrings that satisfy the condition above. A substring is a
 * contiguous sequence of characters within a string. 
 * 
 * Example 1: Input: s = "aba", t = "baba" Output: 6 
 * 
 * Explanation: The following are the pairs of
 * substrings from s and t that differ by exactly 1 character: ("aba", "baba")
 * ("aba", "baba") ("aba", "baba") ("aba", "baba") ("aba", "baba") ("aba",
 * "baba") The underlined portions are the substrings that are chosen from s and
 * t.
 */
public class Solution1638 {

    public int countSubstrings(String s, String t) {
        int ans = 0;
        int lenS = s.length();
        int lenT = t.length();

        for (int i = 0; i < lenS; i++) {
            for (int j = 0; j < lenT; j++) {// check all strings starting at all possible combinations (i,j)
                int x = i;
                int y = j;
                int diff = 0;
                while (x < lenS && y < lenT) {
                    if (s.charAt(x) != t.charAt(y)) {
                        diff++;
                    }
                    if (diff == 1) {
                        ans++;
                    }
                    if (diff == 2) {
                        break;
                    }
                    x++;
                    y++;
                }
            }
        }
        return ans;
    }

}
