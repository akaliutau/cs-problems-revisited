package com.problems.string;

import java.util.ArrayList;
import java.util.List;

/**
 * String, combinations
 * 
 */
public class Solution17 {

	static List<String> add(String first, String second) {
		List<String> lstRes = new ArrayList<>();
		for (int i = 0; i < first.length(); i++) {
			lstRes.add(first.charAt(i) + second);
		}
		return lstRes;
	}

	public List<String> letterCombinations(String digits) {
		String[] map = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wyxz" };
		char[] digs = digits.toCharArray();
		List<String> res = new ArrayList<>();
		for (int i = digs.length - 1; i > -1; i--) {
			int d = (digs[i] - '0') - 1;
			if (d < 1) {
				continue;
			}
			List<String> lst = new ArrayList<>();
			for (String s : res) {// generate all possible combinations on the bases of existing ones
				lst.addAll(add(map[d], s));
			}
			if (res.isEmpty()) {
				res = add(map[d], "");
			} else {
				res = lst;
			}
		}
		return res;

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
