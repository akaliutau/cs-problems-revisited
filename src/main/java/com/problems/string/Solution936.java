package com.problems.string;

import java.util.ArrayList;
import java.util.List;

/**
 * You want to form a target string of lowercase letters.
 * 
 * At the beginning, your sequence is target.length '?' marks. You also have a
 * stamp of lowercase letters.
 * 
 * On each turn, you may place the stamp over the sequence, and replace every
 * letter in the sequence with the corresponding letter from the stamp. You can
 * make up to 10 * target.length turns.
 * 
 * For example, if the initial sequence is "?????", and your stamp is "abc",
 * then you may make "abc??", "?abc?", "??abc" in the first turn. (Note that the
 * stamp must be fully contained in the boundaries of the sequence in order to
 * stamp.)
 * 
 * If the sequence is possible to stamp, then return an array of the index of
 * the left-most letter being stamped at each turn. If the sequence is not
 * possible to stamp, return an empty array.
 * 
 * For example, if the sequence is "ababc", and the stamp is "abc", then we
 * could return the answer [0, 2], corresponding to the moves "?????" -> "abc??"
 * -> "ababc".
 * 
 * Also, if the sequence is possible to stamp, it is guaranteed it is possible
 * to stamp within 10 * target.length moves. Any answers specifying more than
 * this number of moves will not be accepted.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: stamp = "abc", target = "ababc" Output: [0,2] ([1,0,2] would also be
 * accepted as an answer, as well as some other answers.)
 * 
 * IDEA:
 * Start filling target array with ? from the top to the down
 * 
 * | | | |a|b|c| | |  <-- 3 exact matches
 * | |a|b|?|?|?| | |  <-- 2 exact matches, 1 ?
 * | | | | | |?|b|c|  <-- 2 exact matches, 1 ?
 * |a|b|c| | | | | |  <-- 1 exact matches, 2 ?
 *  
 * Filter out all cases when exactMatch[best] + q[best] < m || all exact matches < m, etc
 *
 */
public class Solution936 {

	/**
	 * Find the best match available return -1 if no found
	 * 
	 * @param base
	 * @param stamp
	 * @return
	 */
	static int partials(char[] base, char[] stamp) {
		int n = base.length;
		int m = stamp.length;
		int[] div = new int[n];
		int[] q = new int[n];
		int[] exactMatch = new int[n];
		for (int i = 0; i < n - m + 1; i++) {
			for (int j = 0; j < m; j++) {
				if (base[i + j] == '?') {
					div[i]++;
					q[i]++;
				} else if (base[i + j] == stamp[j]) {
					div[i]++;
					exactMatch[i]++;
				}
			}
		}
		int best = -1;
		int maxDiv = 0;
		for (int i = 0; i < n - m + 1; i++) {
			if (exactMatch[i] > 0 && div[i] > maxDiv) {
				maxDiv = div[i];
				best = i;
			}
		}
		if (best == -1) {
			return best;
		}
		boolean haveQ = false;
		for (int i = 0; i < m; i++) {
			if (base[best + i] == '?') {
				haveQ = true;
				break;
			}
		}
		if (!haveQ && div[best] < m) {
			return -1;
		}
		if (haveQ && exactMatch[best] + q[best] < m) {
			return -1;
		}
		return best;
	}

	static boolean isFilled(char[] line) {
		for (int i = 0; i < line.length; i++) {
			if (line[i] != '?') {
				return false;
			}
		}
		return true;
	}

	static void fill(char[] line, int from, int len) {
		for (int i = from; i < from + len; i++) {
			line[i] = '?';
		}
	}

	public int[] movesToStamp(String stamp, String target) {
		char[] base = target.toCharArray();
		char[] stp = stamp.toCharArray();
		List<Integer> ans = new ArrayList<>();
		for (int cycle = 0; cycle < 10 * base.length; cycle++) {
			int offset = partials(base, stp);
			if (offset == -1) {
				break;
			}
			ans.add(offset);
			fill(base, offset, stp.length);
			if (isFilled(base)) {
				break;
			}
		}
		if (!isFilled(base)) {
			return new int[0];
		}
		int[] ret = new int[ans.size()];
		int idx = 0;
		for (int i = ans.size() - 1; i > -1; i--) {
			ret[idx++] = ans.get(i);
		}
		return ret;

	}
}
