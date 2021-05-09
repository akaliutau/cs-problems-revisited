package problem.backtracking;

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
 *        \  | \
 *         ) (  )
 *          \
 *           )
 *            \
 *             )
 *       
 */
public class Solution22 {

	void traverse(List<String> accumulator, String cur, int open, int close, int max) {
		if (cur.length() == max * 2) {
			accumulator.add(cur);
			return;
		}
		// this block will be invoked until ((( formed - left branch of brackets tree 
		if (open < max) {
			traverse(accumulator, cur + "(", open + 1, close, max);
		}
		
		// this block will be invoked when right part is formed - right branch of brackets tree 
		if (close < open) {
			traverse(accumulator, cur + ")", open, close + 1, max);
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> accumulator = new ArrayList<>();
		traverse(accumulator, "", 0, 0, n);
		return accumulator;
	}

}
