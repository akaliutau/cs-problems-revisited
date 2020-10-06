package com.problems.string;

import java.util.Stack;

/**
 * String
 * 
 * 3 - 2*2
 * 
 * idea: split equation into groups divided by "+" sign
 * 
 * (3) + ( -2*2)
 * all * and / operations are calc in place
 * 
 *  stack accumulator   operation
 *  []      3            +
 *  [3]     0            -
 *  [3]     2            -
 *  [3, -2] 0            *
 *  [3, -2] 2            *
 *  [3, -4] 0 
 *         
 *  reduce [3, -4] -> -1
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
            char operation = '+';// last op is always summing
            int accumulator = 0;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    accumulator = accumulator * 10 + (c - '0');
                }
                if (isOperator(c) || i == n - 1) {
                    if (operation == '+') {
                        stack.push(accumulator);
                    } else if (operation == '-') {
                        stack.push(-accumulator);
                    } else if (operation == '*') {
                        stack.push(stack.pop() * accumulator);
                    } else if (operation == '/') {
                        stack.push(stack.pop() / accumulator);
                    }
                    accumulator = 0;
                    operation = c;
                }
            }
            while (!stack.isEmpty()) {
                result = result + stack.pop();
            }
        }
        return result;
    }


    public static void main(String[] arg) {

        System.out.println();

    }

}
