package com.problems.dfs;

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

    void dfs(String s, Set<String> res, Set<String> memo) {
            if (valid(s)) {
                res.add(s);
                return;
            }

        int left = 0;
        int last = -1;
        for (int i = 0; i < s.length(); i++) {
            left += left(s.charAt(i));
            if (left < 0) {// too much )
                for (int j = last + 1; j <= i; j++) {// try to remove some )
                    if (s.charAt(j) == ')') {
                        String candidate = s.substring(0, j) + s.substring(j + 1);
                        if (!memo.contains(candidate)) {
                            memo.add(candidate);
                            // remove j and continue dfs
                            dfs(candidate, res, memo);
                        }
                    }
                }
                last = i;
                left = 0;
            }
        }

        int right = 0;
        last = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            right += right(s.charAt(i));
            if (right < 0) {
                for (int j = last - 1; j >= i; j--) {
                    if (s.charAt(j) == '(') {
                        String candidate = s.substring(0, j) + s.substring(j + 1);
                        if (!memo.contains(candidate)) {
                            memo.add(candidate);
                            // remove j and continue dfs
                            dfs(candidate, res, memo);
                        }
                    }
                }
                last = i;
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
