package problem.slidingwindow;

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
 * IDEA: 
 * 
 * 1) The sliding window slides over the string s and validates each character. 
 * 
 * Based on certain conditions, the sliding window either expands or shrinks, namely: 
 * 
 *       === A substring is valid if each character has at least k frequency === 
 * 
 * find all valid substrings with a different number of distinct characters and track the maximum
 * length
 * 
 * O(nU)
 */
public class Solution395 {

    // get the maximum number of distinct letters in the string s
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
        
        
        for (int cur = 1; cur <= maxUnique; cur++) {// have to check all variants as replacement for check all subarrays of all lengths
            // reset countMap
            Arrays.fill(countMap, 0);
            int left = 0, right = 0, ch = 0;
            int distinct = 0;// total distinct letters in [left, right]
            int countAtLeastK = 0;
            while (right < str.length) {

                if (distinct <= cur) {// expand the sliding window
                    ch = str[right] - 'a';
                    countMap[ch]++;
                    if (countMap[ch] == 1) {
                        distinct++;
                    }
                    if (countMap[ch] == k) {
                        countAtLeastK++;
                    }
                    right++;
                } else {// shrink the sliding window
                    ch = str[left] - 'a';
                    countMap[ch]--;
                    if (countMap[ch] == 0) {
                        distinct--;
                    }
                    if (countMap[ch] < k) {// fell under threshold
                        countAtLeastK--;
                    }
                    left++;
                }
                if (distinct == cur && distinct == countAtLeastK)
                    result = Math.max(right - left, result);
            }
        }

        return result;
    }

}
