package problem.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given a text string and words (a list of strings), return all index pairs [i,
 * j] so that the substring text[i]...text[j] is in the list of words. Example
 * 1:
 * 
 * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
 * Output: [[3,7],[9,13],[10,17]]
 */
public class Solution1065 {
	public int[][] indexPairs(String text, String[] words) {
		List<int[]> ans = new ArrayList<>();
		for (String word : words) {
			int from = 0;
			while (text.indexOf(word, from) > -1) {
				int offset = text.indexOf(word, from);
				ans.add(new int[] { offset, offset + word.length() - 1 });
				from = offset + 1;
			}
		}
		Comparator<int[]> byStart = (o, p) -> o[0] - p[0];
		Comparator<int[]> byEnd = (o, p) -> o[1] - p[1];
		Collections.sort(ans, byStart.thenComparing(byEnd));
		int[][] ret = new int[ans.size()][2];
		for (int i = 0; i < ans.size(); i++) {
			ret[i] = ans.get(i);
		}
		return ret;
	}
}
