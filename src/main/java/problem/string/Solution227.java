package problem.string;

import java.util.Stack;

/**
 * Evaluate equation in string form
 * 
 * f.e.
 * 
 * 3 - 2*2
 *    \  \
 *     \  when met '*', process '-' 
 * 
 * 
 *  stack  num        numSign
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
 *  1. sign is always coming BEFORE number (no sign in the beginning can be interpreted as a '+')
 *  2. when sign is met, perform operation with data on the top of Stack using PREVIOUS operator 
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
            char numSign = '+'; //  <--------- last op is always summing
            int num = 0; //    <-------------- lst parsed number
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                    continue;
                }
                if (isOperator(c) || i == n - 1) {// Crucial moment: note we exec this block on c, but analyze operation on stack!
                    if (numSign == '+') {
                        stack.push(num);          // [3]
                    } else if (numSign == '-') {  // [3, -2]
                        stack.push(-num);
                    } else if (numSign == '*') {
                        stack.push(stack.pop() * num);
                    } else if (numSign == '/') {
                        stack.push(stack.pop() / num);
                    }
                    num = 0;
                    numSign = c;
                }
            }
            while (!stack.isEmpty()) {
                result = result + stack.pop();
            }
        }
        return result;
    }

}
