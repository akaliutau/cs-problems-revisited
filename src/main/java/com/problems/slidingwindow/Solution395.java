package com.problems.slidingwindow;

import java.util.Arrays;

/**
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than k
 * times. 
 * 
 * Example 1: Input: s = "aaabb", k = 3 Output: 3 The longest substring
 * is "aaa", as 'a' is repeated 3 times. 
 * 
 * 
 * IDEA: The sliding window slides over
 * the string s and validates each character. Based on certain conditions, the
 * sliding window either expands or shrinks. A substring is valid if each
 * character has at least k frequency. 
 * 
 * The idea is to find all the valid
 * substrings with a different number of unique characters and track the maximum
 * length
 */
public class Solution395 {

    // get the maximum number of unique letters in the string s
    int getMaxUniqueLetters(String s) {
        boolean map[] = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map[s.charAt(i) - 'a']) {
                maxUnique++;
                map[s.charAt(i) - 'a'] = true;
            }
        }
        return maxUnique;
    }

    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] countMap = new int[26];
        int maxUnique = getMaxUniqueLetters(s);
        int result = 0;
        for (int cur = 1; cur <= maxUnique; cur++) {
            // reset countMap
            Arrays.fill(countMap, 0);
            int left = 0, right = 0, idx = 0, unique = 0, countAtLeastK = 0;
            while (right < str.length) {

                if (unique <= cur) {// expand the sliding window
                    idx = str[right] - 'a';
                    if (countMap[idx] == 0) {
                        unique++;
                    }
                    countMap[idx]++;
                    if (countMap[idx] == k) {
                        countAtLeastK++;
                    }
                    right++;
                } else {// shrink the sliding window
                    idx = str[left] - 'a';
                    if (countMap[idx] == k) {
                        countAtLeastK--;
                    }
                    countMap[idx]--;
                    if (countMap[idx] == 0) {
                        unique--;
                    }
                    left++;
                }
                if (unique == cur && unique == countAtLeastK)
                    result = Math.max(right - left, result);
            }
        }

        return result;
    }

}
