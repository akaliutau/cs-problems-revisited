package com.problems.string;

import java.util.Arrays;

/**
 * Given two strings s and t, determine if they are isomorphic. Two strings are
 * isomorphic if the characters in s can be replaced by t. All occurrences
 * of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a
 * character may map to itself. 
 * 
 * Example 1: Input: s = "egg", t = "add" Output: true
 * 
 * IDEA:
 *  use 2 maps for source and dest
 */
public class Solution205 {

    public boolean isIsomorphic(String s, String t) {
        int[] mapping = new int[256];
        boolean[] mapped = new boolean[256];
        Arrays.fill(mapping, -1);

        if (s.length() != t.length()) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            int from = s.charAt(i);
            int dest = t.charAt(i);
            
            if (mapping[from] == -1) {// mapping absent
                if (!mapped[dest]) {// dest is not mapped yet
                    mapping[from] = dest;
                    mapped[dest] = true;
                }else {
                    return false;
                }
            }else {// have mapping
               if (mapping[from] != dest) {// break only if error
                    return false;
                }
            }
        }
        return true;
    }

  

}
