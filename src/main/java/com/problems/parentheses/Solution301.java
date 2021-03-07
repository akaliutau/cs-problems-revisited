package com.problems.parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results. Note: The input string may contain
 * letters other than the parentheses ( and ). 
 * 
 * Example 1: Input: "()())()"
 * Output: ["()()()", "(())()"]
 * 
 * IDEA:
 * 1) count left and right balance:
 *   f.e. for the left balance:
 *   a) inc counter if char == '(' and dec if char == ')' - as a result all situations like ()) will be covered
 *      regardless of any further symbols, the only way to fix the problem is to remove ANY of )-brackets
 *       
 * 
 */
public class Solution301 {

    int left(char c){
        if (c != '(' && c!= ')') return 0;
        return c == '(' ? 1 : -1;
    }

    int right(char c){
        if (c != '(' && c!= ')') return 0;
        return c == '(' ? -1 : 1;
    }

    boolean valid(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            left += left(s.charAt(i));
            if (left < 0) {
                return false;
            }
        }
        return left == 0;
    }
    
    void processWithoutBracket(String orig, int j, Set<String> memo, Set<String> res) {
        String candidate = orig.substring(0, j) + orig.substring(j + 1);
        if (!memo.contains(candidate)) {// omit already processed
            memo.add(candidate);
            // remove j and continue dfs
            dfs(candidate, res, memo);
        }
    }

    void dfs(String s, Set<String> res, Set<String> memo) {
        if (valid(s)) {
             res.add(s);
             return;
        }

        int left = 0;
        int lastRemoved = -1;// first time: starting from 0, then from i
        for (int i = 0; i < s.length(); i++) {
            left += left(s.charAt(i));
            if (left < 0) {// too much )
                for (int j = lastRemoved + 1; j <= i; j++) {// try to remove some )-brackets
                    if (s.charAt(j) == ')') {
                    	processWithoutBracket(s, j, memo, res);
                    }
                }
                lastRemoved = i;
                left = 0;
            }
        }

        int right = 0;
        lastRemoved = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            right += right(s.charAt(i));
            if (right < 0) {
                for (int j = lastRemoved - 1; j >= i; j--) {// try to remove some (-brackets
                    if (s.charAt(j) == '(') {
                    	processWithoutBracket(s, j, memo, res);
                    }
                }
                lastRemoved = i;
                right = 0;
            }
        }

    }

    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        Set<String> memo = new HashSet<>();
        dfs(s, res, memo);
        return new ArrayList<String>(res);
    }

}
