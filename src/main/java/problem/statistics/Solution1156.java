package problem.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string text, we are allowed to swap two of the characters in the
 * string. Find the length of the longest substring with repeated characters.
 * 
 * Example 1:
 * 
 * Input: text = "ababa" Output: 3
 * 
 * IDEA: 
 * 
 * use 3 different available strategies to perform an effective merge
 *
 */
public class Solution1156 {
	public int maxRepOpt1(String text) {
		int n = text.length();
		List<int[]> stat = new ArrayList<>();
		char[] letters = text.toCharArray();

		int[] prev = new int[] { letters[0], 1 };
		for (int i = 1; i < n; i++) {
			int ch = letters[i];
			if (ch == prev[0]) {
				prev[1]++;
			} else {
				stat.add(prev);
				prev = new int[] { ch, 1 };
			}
		}
		stat.add(prev);
		
		if (stat.size() == 1) {
			return stat.get(0)[1];
		}
		if (stat.size() == 2) {
			return Math.max(stat.get(0)[1], stat.get(1)[1]);
		}

		int maxSize = 1;
		int[] freq = new int[256];

		// strategy 1
		// just a max block
		for (int[] block : stat) {
			maxSize = Math.max(maxSize, block[1]);
			freq[block[0]]++;
		}

		// strategy 2
		// 
		// aaabaaa
		// a b a         any other any
		// 3 1 3            N 1 M

		for (int i = 1; i < stat.size() - 1; i++) {
			int[] left = stat.get(i - 1);
			int[] center = stat.get(i);
			int[] right = stat.get(i + 1);
			if (left[0] != right[0])
				continue;
			if (center[1] == 1) {
				int length = freq[left[0]] <= 2 ? left[1] + right[1] : left[1] + right[1] + 1;
				maxSize = Math.max(maxSize, length);
			} else if (center[1] > 1) {
				maxSize = Math.max(maxSize, Math.max(left[1], right[1]) + 1);
			}
		}
		// strategy 3
		// pair of non-neighbor blocks
		// ashgdaaaaaa
		for (int l = 0; l < 256; l++) {
			if (freq[l] >= 2) {
				for (int[] block : stat) {
					if (block[0] == l)
						maxSize = Math.max(maxSize, block[1] + 1);
				}
			}
		}

		return maxSize;

	}
}
