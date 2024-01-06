package problem.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, 
 * return all possible letter combinations that the number could represent. Return the answer in any order.
 * 
 * IDEA:
 * Generates a list of possibilities using prefix like "abc" and suffices from all previous steps
 * 
 * 1st iteration: [] -> [a, b, c]
 * 1st iteration: [a, b, c] -> [aa, ba, ca, ab, bb, cb, ac, bc, cc] - Cartesian multiplication
 * 
 */
public class Solution17 {

	/**
	 * Generates a list of possibilities using prefix like "abc" and suffices from all previous steps
	 * @param prefix
	 * @param suffix
	 * @return
	 */
 	static List<String> add(String prefix, String first) {
		List<String> lstRes = new ArrayList<>();
		for (int i = 0; i < first.length(); i++) {
			lstRes.add(prefix + first.charAt(i));
		}
		return lstRes;
	}
	
	public List<String> letterCombinations(String digits) {
		String[] map = {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wyxz"};
		char[] digs = digits.toCharArray();
		List<String> res = new ArrayList<>();
		for (int i = 0; i < digs.length; i++) {
			int d = (digs[i] - '0') - 1;
			if (d < 1) {
				continue;
			}
			List<String> lst = new ArrayList<>();
			for (String s : res) {
				lst.addAll(add(s, map[d]));
			}
			if (res.isEmpty()) {
				res = add("", map[d]);
			}else {
				res = lst;
			}
		}
		return res;

	}
}
