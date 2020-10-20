package com.problems.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string s, return true if s matches the pattern. A
 * string s matches a pattern if there is some bijective mapping of single
 * characters to strings such that if each character in pattern is replaced by
 * the string it maps to, then the resulting string is s. A bijective mapping
 * means that no two characters map to the same string, and no character maps to
 * two different strings. 
 * 
 * Example 1: Input: pattern = "abab", s =
 * "redblueredblue" Output: true 
 * 
 * Explanation: One possible mapping is as
 * follows: 'a' -> "red" 'b' -> "blue"
 */
public class Solution291 {

    Map<Character, String> map = new HashMap<>();
    Set<String> dup = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String s) {
        return backtracking(pattern.toCharArray(), s);
    }

    boolean backtracking(char[] pattern, String s) {
        if (pattern.length == 0 && s.length() == 0)
            return true;
        if (pattern.length == 0 || s.length() == 0)
            return false;

        char key = pattern[0];
        if (map.containsKey(key)) {
            String val = map.get(key);
            if (s.startsWith(val))
                return backtracking(Arrays.copyOfRange(pattern, 1, pattern.length), s.substring(val.length()));
            else
                return false;
        } else {
            for (int i = 1; i <= s.length() - pattern.length + 1; i++) {
                String tmp = s.substring(0, i);
                if (!dup.add(tmp))
                    continue;
                map.put(key, tmp);
                if (backtracking(Arrays.copyOfRange(pattern, 1, pattern.length), s.substring(i)))
                    return true;
                map.remove(key);
                dup.remove(tmp);
            }
            return false;
        }
    }

}
