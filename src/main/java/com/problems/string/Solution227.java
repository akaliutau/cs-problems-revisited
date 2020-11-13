package com.problems.string;

import java.util.Stack;

/**
 * String
 * 
 * 3 - 2*2
 * 
 *  stack num   numSign
 *  []      3            +
 *  [3]     0            -
 *  [3]     2            -
 *  [3, -2] 0            *
 *  [3, -2] 2            *
 *  [3, -4] 0 
 *         
 *  reduce [3, -4] -> -1
 *  
 *  IDEA:
 *  sign is always coming before number  
 * 
 */
public class Solution227 {
    
    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public int calculate(String s) {
        int result = 0;
        if (s == null) {
            return 0;
        }
        int n = s.length();
        if (n > 0) {
            Stack<Integer> stack = new Stack<>();
            char numSign = '+';// last op is always summing
            int num = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    num = num * 10 + (c - '0');
                }
                if (isOperator(c) || i == n - 1) {
                    if (numSign == '+') {
                        stack.push(num);
                    } else if (numSign == '-') {
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
