package com.problems.parentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
 *   f.e. for the leftView balance:
 *   a) increment counter if char == '(' 
 *    and decrement if char == ')' - 
 *    as a result all situations like ()) will be covered
 *    if we get balance < 0, remove problem symbol which is extra ')' and investigate this case through dfs
 *       
 * 
 */
public class Solution301 {

	   int leftView(char c){
	        if (c != '(' && c!= ')') return 0;
	        return c == '(' ? 1 : -1;
	    }

	    int rightView(char c){
	        if (c != '(' && c!= ')') return 0;
	        return c == '(' ? -1 : 1;
	    }

	    boolean valid(String s) {
	        int left = 0;
	        for (int i = 0; i < s.length(); i++) {
	            left += leftView(s.charAt(i));// can be rightView as well
	            if (left < 0) {
	                return false;
	            }
	        }
	        return left == 0;
	    }
	    
	    void processWithoutMiddle(String orig, int j, Set<String> memo, Set<String> res) {
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
	        Queue<Integer> rightBrackets = new LinkedList<>();
	        for (int i = 0; i < s.length(); i++) {
	            left += leftView(s.charAt(i));
	            if (s.charAt(i) == ')') {
	            	rightBrackets.add(i);
	            }
	            if (left < 0) {// too much ')'
	                for (int j : rightBrackets) {// try to remove any consequent )-brackets
	                   	processWithoutMiddle(s, j, memo, res);
	                }
	                left = 0;
	            }
	        }

	        int right = 0;
	        Queue<Integer> leftBrackets = new LinkedList<>();
	        for (int i = s.length() - 1; i >= 0; i--) {
	            right += rightView(s.charAt(i));
	            if (s.charAt(i) == '(') {
	            	leftBrackets.add(i);
	            }
	           if (right < 0) {// too much '('
	                for (int j : leftBrackets) {// try to remove any consequent (-brackets
	                   	processWithoutMiddle(s, j, memo, res);
	                }
	                right = 0;
	            }
	        }

	    }

	    public List<String> removeInvalidParentheses(String s) {
	        Set<String> res = new HashSet<>();
	        Set<String> memo = new HashSet<>();
	        dfs(s, res, memo);
	        if (res.isEmpty()){
	            return Arrays.asList("");
	        }
	        return new ArrayList<>(res);
	    }

}
