package com.problems.string;

/**
 * Given two strings s and t, find the number of ways you can choose a non-empty
 * substring of s and replace a single character by a different character such
 * that the resulting substring is a substring of t. In other words, find the
 * number of substrings in s that differ from some substring in t by exactly one
 * character. 
 * 
 * For example, the underlined substrings in 
 * "compute r" and "computa tion" 
 *  -------        --------
 *  
 * only differ by the 'e'/'a', so this is a valid way. Return the
 * number of substrings that satisfy the condition above. A substring is a
 * contiguous sequence of characters within a string. 
 * 
 * Example 1: Input: s = "aba", t = "baba" Output: 6 
 * 
 * Explanation: The following are the pairs of
 * substrings from s and t that differ by exactly 1 character: ("a", "b")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * 
 * 
 * The underlined portions are the substrings (only of size=1) that are chosen from s and
 * t.
 * 
 * IDEA:
 * 1) check all strings starting at all possible combinations (i,j)
 * 2) for each possible starting indecies of substr (i,j)
 *   initiate a comparison procedure for some substrings of s and t
 * 3) increase counter each time the divergence is found
 * 4) exit comparison procedure if there are 2+ differences
 */
public class Solution1638 {

    public int countSubstrings(String s, String t) {
        int ans = 0;
        int lenS = s.length();
        int lenT = t.length();

        for (int i = 0; i < lenS; i++) {
            for (int j = 0; j < lenT; j++) {
            	// check all strings starting at all possible combinations (i,j)
                int sStartingAt = i;
                int tStartingAt = j;
                // initiate a comparison procedure for some substrings of s and t
                int diff = 0;
                while (sStartingAt < lenS && tStartingAt < lenT) {
                    if (s.charAt(sStartingAt) != t.charAt(tStartingAt)) {
                        diff++;
                    }
                    if (diff == 1) {// find some 2 substrings from s and t 
                        ans++;
                    }
                    if (diff == 2) {// exit procedure if there are more than 2 differences
                        break;      // NOTE:  s and t will be ending at (non-important index) 
                    }
                    sStartingAt ++;
                    tStartingAt ++;
                }
            }
        }
        return ans;
    }

}
