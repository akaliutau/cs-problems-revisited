package problem.twopointers;

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
 * IDEA:
 * during traversing take care of upperBoundary for all letters seen so far
 * if it coincides with i, then => from [lastCut,curIdx] we have seen all letters
 * 
 * 012345
 * abcadd
 * 
 * a => 3     <--- note: the last seen of 'a'
 * b => 1
 * c => 2
 * d => 5
 * 
 * upperBoundary will be pushed up each time the last seen index of letter exceeds the limit
 * if we traversed so far and we can cut here, and all letters seen so far will be under upperBoundary
 */
public class Solution763 {
	public List<Integer> partitionLabels(String s) {
		int[] lastSeenAt = new int[26];
		int n = s.length();
		for (int i = 0; i < n; i++) {
			lastSeenAt[s.charAt(i) - 'a'] = i;
		}

		int upperBoundary = 0;// upperBoundary is the lower upper boundary for all letters seen so far
		int start = 0;
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			int curLetter = s.charAt(i) - 'a';
			if (lastSeenAt[curLetter] > upperBoundary) {
				upperBoundary = lastSeenAt[curLetter];
			}

			if (i == upperBoundary) {// triggers the cut
				result.add(upperBoundary - start + 1);
				start = i + 1; // update start after cut
			}
		}

		return result;

	}
}
