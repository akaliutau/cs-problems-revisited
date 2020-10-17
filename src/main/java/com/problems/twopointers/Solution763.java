package com.problems.twopointers;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * A string S of lowercase English letters is given. We want to partition this
 * string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: S = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The
 * partition is "ababcbaca", "defegde", "hijhklij". This is a partition so that
 * each letter appears in at most one part.
 * 
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it
 * splits S into less parts.
 * 
 * 
 * 
 */
public class Solution763 {
	public List<Integer> partitionLabels(String s) {
		int[] letterSeenAt = new int[26];
		int n = s.length();
		for (int i = 0; i < n; i++) {
			letterSeenAt[s.charAt(i) - 'a'] = i;
		}

		int end = 0;
		int start = 0;
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (letterSeenAt[s.charAt(i) - 'a'] > end) {
				end = letterSeenAt[s.charAt(i) - 'a'];
			}

			if (i == end) {// triggers the cut
				result.add(end - start + 1);
				start = i + 1;
			}
		}

		return result;

	}
}
