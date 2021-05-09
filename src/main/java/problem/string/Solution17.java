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
	static List<String> add(String prefix, String suffix) {
		List<String> lstRes = new ArrayList<>();
		for (int i = 0; i < prefix.length(); i++) {
			lstRes.add(prefix.charAt(i) + suffix);
		}
		return lstRes;
	}

	public List<String> letterCombinations(String digits) {
		String[] map = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wyxz" };
		char[] digs = digits.toCharArray();
		List<String> res = new ArrayList<>();
		for (int i = digs.length - 1; i > -1; i--) {
			int d = (digs[i] - '0') - 1;
			if (d < 1) {// drop 0
				continue;
			}
			List<String> lst = new ArrayList<>();
			for (String s : res) {// generate all possible combinations on the bases of existing ones
				lst.addAll(add(map[d], s));
			}
			if (res.isEmpty()) {
				res = add(map[d], "");// covers the very first iteration
			} else {
				res = lst;
			}
		}
		return res;

	}



}
