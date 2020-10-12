package com.problems.stack;

import java.util.Stack;

/**
 * Stack
 */
public class Solution224 {

    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> signs = new Stack<>();
               int operand = 0;
        int result = 0; 
        int sign = 1; 

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                operand = 10 * operand + (int) (ch - '0');
            } else if (ch == '+') {
               result += sign * operand;
                sign = 1;
                operand = 0;
            } else if (ch == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                stack.push(result);
                signs.push(sign);
                // Reset operand and result, as if new evaluation begins for the new
                // sub-expression
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                result += sign * operand;
                result *= signs.pop();
                result += stack.pop();
                // Reset the operand
                operand = 0;
            }
        }
        return result + (sign * operand);
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
