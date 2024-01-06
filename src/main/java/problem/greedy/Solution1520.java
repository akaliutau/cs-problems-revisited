package problem.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of
 * non-empty substrings of s that meet the following conditions: The substrings
 * do not overlap, that is for any two substrings s[i..j] and s[k..l], either j
 * < k or i > l is true. A substring that contains a certain character c must
 * also contain all occurrences of c. Find the maximum number of substrings that
 * meet the above conditions. If there are multiple solutions with the same
 * number of substrings, return the one with minimum total length. It can be
 * shown that there exists a unique solution of minimum total length. Notice
 * that you can return the substrings in any order. 
 * 
 * Example 1: Input: s =
 * "adefaddaccc" 
 * Output: ["e","f","ccc"] 
 * 
 * Explanation: The following are all the
 * possible substrings that meet the conditions: 
 * [ 
 * "adefaddaccc" 
 * "adefadda",
 * "ef", 
 * "e", 
 * "f", 
 * "ccc", 
 * ]
 * 
 */
public class Solution1520 {

    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        int[] start = new int[26];
        int[] end = new int[26];
        char[] let = s.toCharArray();

        Arrays.fill(start, Integer.MAX_VALUE);
        Arrays.fill(end, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            start[let[i] - 'a'] = Math.min(start[let[i] - 'a'], i);// lower boundary for letter
            end[let[i] - 'a'] = Math.max(end[let[i] - 'a'], i);// upper boundary for letter
        }

        List<int[]> intervals = new ArrayList<>();

        // generate intervals in accordance with conditions

        for (int i = 0; i < 26; i++) {// for each letter
            if (start[i] == Integer.MAX_VALUE) {// no such letter
                continue;
            }
            int left = start[i], right = end[i];
            boolean valid = true;
            for (int j = left + 1; j < right; j++) {
                if (start[let[j] - 'a'] < left) {// all other letters
                    valid = false;
                    break;
                }
                right = Math.max(right, end[let[j] - 'a']);// interval must incl all letters
            }
            if (valid) {
                intervals.add(new int[] { left, right });
            }
        }

        // sort them, starting with the smallest
        Collections.sort(intervals, (o, p) -> Integer.compare(o[1] - o[0], p[1] - p[0]));

        int len = intervals.size();
        boolean[] valid = new boolean[len];
        Arrays.fill(valid, true);

        // check intersection
        for (int i = 0; i < len; i++) {
            if (!valid[i]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (intervals.get(j)[0] <= intervals.get(i)[0] && intervals.get(i)[1] <= intervals.get(j)[1]) {
                    valid[j] = false;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if (valid[i]) {
                res.add(s.substring(intervals.get(i)[0], intervals.get(i)[1] + 1));
            }
        }
        return res;
    }

 

}
