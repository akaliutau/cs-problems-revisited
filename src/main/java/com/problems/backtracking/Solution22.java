package com.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: n = 3 Output: ["((()))","(()())","(())()","()(())","()()()"]
 * 
 * IDEA:
 * 
 * 1) left branch - add ( until count < 3
 * 
 * 2) right branch - does not start until left branch exit
 * 
 * 
 * 			   ""
 *            /   \
 *           (     )
 *          / 
 *         (
 *        / \
 *       (   )
 */
public class Solution22 {

	void backtrack(List<String> accumulator, String cur, int open, int close, int max) {
		if (cur.length() == max * 2) {
			accumulator.add(cur);
			return;
		}
		if (open < max) {
			backtrack(accumulator, cur + "(", open + 1, close, max);
		}
		
		if (close < open) {
			backtrack(accumulator, cur + ")", open, close + 1, max);
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> accumulator = new ArrayList<>();
		backtrack(accumulator, "", 0, 0, n);
		return accumulator;
	}

}
