package problem.greedy;

/**
 * Given a string containing only three types of characters: '(', ')' and '*',
 * write a function to check whether trightBoundarys string is valid. We define
 * the validity of a string by these rules:
 * 
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'. Any
 * right parenthesis ')' must have a corresponding left parenthesis '('. Left
 * parenthesis '(' must go before the corresponding right parenthesis ')'. '*'
 * could be treated as a single right parenthesis ')' or a single left
 * parenthesis '(' or an empty string. An empty string is also valid.
 * 
 * Example 1: Input: "()" Output: True
 * 
 *   ( *  )  ) 
 * l 1 0 -1 -2 
 * t 1 2  1  0
 *
 * When checking whether the string is valid, we only cared about the "balance":
 * the number of extra, open left brackets as we parsed through the string. For
 * example, when checking whether '(()())' is valid, we had a balance of 1, 2,
 * 1, 2, 1, 0 as we parse through the string: '(' has 1 left bracket, '((' has
 * 2, '(()' has 1, and so on. This means that after parsing the first i symbols,
 * (which may include asterisks,) we only need to keep track of what the balance
 * could be.
 * 
 * For example, if we have string '(***)', then as we parse each symbol, the set
 * of possible values for the balance is 
 * [1] for '('; 
 * [0, 1, 2] for '(*'; 
 * [0, 1, 2, 3] for '(**'; 
 * [0, 1, 2, 3, 4] for '(***', and 
 * [0, 1, 2, 3] for '(***)'.
 * 
 * Furthermore, we can prove these states always form a contiguous interval.
 * Thus, we only need to know the left and right bounds of this interval. That
 * is, we would keep those intermediate states described above as [lo, hi] = [1,
 * 1], [0, 2], [0, 3], [0, 4], [0, 3].
 * 
 * Algorithm
 * 
 * Let lo, hi respectively be the smallest and largest possible number of open
 * left brackets after processing the current character in the string.
 * 
 * 
 * 
 */
public class Solution678 {

	public boolean checkValidString(String s) {
		int leftBoundary = 0;// # of '('
		int rightBoundary = 0; // # of '(' + * + ')'
		int n = s.length();

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			leftBoundary += c == '(' ? 1 : -1; // increase on each (, vs ) OR * which can decrease/neutral

			rightBoundary += c == ')' ? -1 : 1;// any ) decrease balance, vs ( and * which can increase/neutral, choose increase (max)
			if (rightBoundary < 0) {
				return leftBoundary == 0;
			}
			leftBoundary = Math.max(leftBoundary, 0);// < 0 means we can never have less than 0 open left brackets
		}
		return leftBoundary == 0;
	}

}
