package problem.string;

import java.util.Stack;

/**
 * Evaluate equation in string form
 * 
 * f.e.
 * 
 * 3 - 2*2
 *    \  \
 *     \  when met '*', process '-' - this is because at this moment we already know all ingredients 
 * 
 * 
 *  stack  num        operator
 *  []      3            +  <-- this is what we are going to assign to number on the TOP OF STACK after number parsing
 *  [3]     null         -  <-- assign this to 2 after it's been parsed --- 
 *  [3]     2            -                                                 |
 *  [3, -2] null         *      <------------------------------------------
 *  [3, -2] 2            *  <-- apply this to 2 AND the top of stack, then put the result back to stack
 *  [3, -4] null 
 *         
 *  reduce [3, -4] -> -1
 *  
 *  IDEA:
 *  1. operator is always coming BEFORE number (no operator in the beginning can be interpreted as a '+')
 *  2. when operator is met, perform operation with data on the top of Stack using PREVIOUS operator 
 *  3. 
 *  
 * 
 */
public class Solution227 {
    
    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.length();
        int result = 0;
        if (n > 0) {
            Stack<Integer> stack = new Stack<>();
            char operator = '+'; //  <--------- last op is always summing
            int num = 0; //    <-------------- lst parsed number
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                    continue;
                }
                if (isOperator(c) || i == n - 1) {// Crucial moment: note we exec this block on c, but analyze operation on stack!
                    if (operator == '+') {
                        stack.push(num);          // [3]
                    } else if (operator == '-') {  // [3, -2]
                        stack.push(-num);
                    } else if (operator == '*') {
                        stack.push(stack.pop() * num);
                    } else if (operator == '/') {
                        stack.push(stack.pop() / num);
                    }
                    num = 0;
                    operator = c;
                }
            }
            while (!stack.isEmpty()) {
                result = result + stack.pop();
            }
        }
        return result;
    }

}
