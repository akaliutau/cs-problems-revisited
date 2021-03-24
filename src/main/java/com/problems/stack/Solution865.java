package com.problems.stack;

/**
 * Given a balanced parentheses string S, compute the score of the string based
 * on the following rule: 
 * () has score 1 
 * AB has score A + B, where A and B are
 * balanced parentheses strings. 
 * (A) has score 2 * A, where A is a balanced
 * parentheses string. 
 * 
 * Example 1: Input: "()" Output: 1 Example 2: Input: "(())"
 * Output: 2
 * 
 * IDEA:
 * 
 * 
 */
public class Solution865 {

    public int scoreOfParentheses(String s) {
        int ans = 0;
        int balance = 0;
        char[] let = s.toCharArray();
        for (int i = 0; i < s.length(); ++i) {;
            if (let[i] == '(') {
            	balance++;
            } else {
            	balance--;
                if (let[i-1] == '(')
                    ans += 1 << balance;
            }
        }

        return ans;
    }
    
}
