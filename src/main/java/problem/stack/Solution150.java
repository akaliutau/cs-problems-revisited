package problem.stack;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression. Note: Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would
 * always evaluate to a result and there won't be any divide by zero operation.
 * 
 * Example 1: Input: ["2", "1", "+", "3", "*"] Output: 9 Explanation: ((2 + 1) *
 * 3) = 9
 * 
 * IDEA:
 * strait-forward: pop 2 values from stack, reduce and push back
 * 
 */
public class Solution150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("*")) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(op1 * op2);
            } else if (token.equals("+")) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(op1 + op2);
            } else if (token.equals("-")) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(op2 - op1);
            } else if (token.equals("/")) {
                int op1 = stack.pop();
                int op2 = stack.pop();
                stack.push(op2 / op1);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.isEmpty() ? 0 : stack.pop();

    }

}
