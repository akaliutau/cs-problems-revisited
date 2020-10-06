package com.problems.twopointers;

import java.util.LinkedList;
import java.util.List;

/**
 * Input: S =   "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The
 * partition is "ababcbaca", "defegde", "hijhklij".
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
