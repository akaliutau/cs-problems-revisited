package com.problems.string;

/**
 * Given two strings s and t, return true if they are both one edit distance
 * apart, otherwise return false. A string s is said to be one distance apart
 * from a string t if you can: Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t. Replace exactly one character
 * of s with a different character to get t. 
 * 
 * Example 1: Input: s = "ab", t =
 * "acb" Output: true Explanation: We can insert 'c' into s to get t.
 * 
 * IDEA:
 *  go to the 1st different symbol and compare the tails
 */
public class Solution161 {

    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }

        // The strings are NOT one edit away distance if the length diff is more than 1.
        if (nt - ns > 1) {
            return false;
        }

        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // if strings have the same length
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                // if strings have different lengths
                else
                    return s.substring(i).equals(t.substring(i + 1));
            }
        }

        // If there is no diffs on ns distance the strings are one edit away only if t has one more character.
        return (ns + 1 == nt);
    }

}
