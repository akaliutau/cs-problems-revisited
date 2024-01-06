package problem.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * You are given a string s representing a list of words. Each letter in the
 * word has one or more options.
 * 
 * If there is one option, the letter is represented as is. If there is more
 * than one option, then curly braces delimit the options. For example,
 * "{a,b,c}" represents options ["a", "b", "c"]. For example, if s = "a{b,c}",
 * the first character is always 'a', but the second character can be 'b' or
 * 'c'. The original list is ["ab", "ac"].
 * 
 * Return all words that can be formed in this manner, sorted in lexicographical
 * order.
 * 
 * Example 1:
 * 
 * Input: s = "{a,b}c{d,e}f" Output: ["acdf","acef","bcdf","bcef"]
 * 
 * IDEA:
 * Create a uniform container for blocks of different sizes and invoke recursive building of the string from that chain
 */
public class Solution1087 {
	void backtracking(int i, StringBuilder str, List<List<String>> data, List<String> acc) {
		if (i == data.size()) {
			acc.add(str.toString());
			return;
		}
		for (String c : data.get(i)) {
			str.append(c);
			backtracking(i + 1, str, data, acc);
			str.setLength(str.length() - 1);
		}

	}

	public String[] expand(String s) {
		List<List<String>> data = new ArrayList<>();
		char[] letters = s.toCharArray();
		List<String> cur = new ArrayList<>();
		StringBuilder block = new StringBuilder();
		for (char c : letters) {
			if (c >= 'a' && c <= 'z') {
				block.append(c);
			} else if (c == '{' || c == '}') {
				if (block.length() > 0) {
					cur.add(block.toString());
				}
				if (!cur.isEmpty()) {
					data.add(cur);
				}
				cur = new ArrayList<>();
				block = new StringBuilder();
			} else if (c == ',') {
				if (block.length() > 0) {
					cur.add(block.toString());
				}
				block = new StringBuilder();
			}
		}
		if (block.length() > 0) {
			cur.add(block.toString());
		}
		if (!cur.isEmpty()) {
			data.add(cur);
		}
		List<String> acc = new ArrayList<>();
		backtracking(0, new StringBuilder(), data, acc);
		Collections.sort(acc);
		String[] ans = new String[acc.size()];
		for (int i = 0; i < acc.size(); i++) {
			ans[i] = acc.get(i);
		}
		return ans;
	}
}
