package problem.stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * Return the result of evaluating a given boolean expression, represented as a
 * string.
 * 
 * An expression can either be:
 * 
 * "t", evaluating to True;
 * 
 * "f", evaluating to False;
 * 
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * 
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner
 * expressions expr1, expr2, ...;
 * 
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner
 * expressions expr1, expr2, ...
 * 
 * 
 * Example 1:
 * 
 * Input: expression = "!(f)" Output: true*
 * 
 * IDEA:
 * 
 * 
 */
public class Solution1106 {

	private static Set<Character> ops = new HashSet<>(Arrays.asList('&', '|', '!'));
	
	static boolean toBoolean(char c) {
		return c == 't';
	}

	static char toString(boolean b) {
		return b ? 't' : 'f';
	}

	static char invert(char c) {
		return c == 't' ? 'f' : 't';
	}


	public boolean parseBoolExpr(String expression) {

		Stack<Character> st = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (c == ')') {

				Queue<Character> q = new LinkedList<>();
				while (!ops.contains(st.peek())) {
					q.add(st.pop());
				}

				Character op = st.pop();

				while (q.size() > 1) {
					boolean b1 = toBoolean(q.poll());
					boolean b2 = toBoolean(q.poll());

					if (op == '&') {
						q.offer(toString(b1 && b2));
					} else if (op == '|') {
						q.offer(toString(b1 || b2));
					}
				}

				if (op == '&' || op == '|') {
					st.push(q.poll());
				} else {
					st.push(invert(q.poll()));
				}

			} else if (c != ',' && c != '(') {
				st.push(c);
			}
		}

		return toBoolean(st.pop());
	}


}
