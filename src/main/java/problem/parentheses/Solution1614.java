package problem.parentheses;

/**
 * 
 * A string is a valid parentheses string (denoted VPS) if it meets one of the
 * following:
 * 
 * 1) It is an empty string "", or a single character not equal to "(" or ")", 
 * 2) It can be written as AB (A concatenated with B), where A and B are VPS's, or 
 * 3) It can be written as (A), where A is a VPS. 
 * 
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 * 
 * depth("") = 0 depth(C) = 0, where C is a string with a single character not equal to "(" or ")". 
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's. 
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS. 
 * 
 * For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and
 * 2), and ")(" and "(()" are not VPS's.
 * 
 * Given a VPS represented as string s, return the nesting depth of s.
 * 
 * Example 1:
 * 
 * Input: s = "(1+(2*3)+((8)/4))+1" Output: 3 
 * 
 * Explanation: Digit 8 is inside of 3 nested parentheses in the string. 
 * 
 * Example 2:
 * 
 * Input: s = "(1)+((2))+(((3)))" Output: 3
 * 
 * IDEA:
 *  use stack (if needed to track exact nested value / position) or depth counter 
 *  with dynamic sticking variable 'maxDepth'
 * 
 */
public class Solution1614 {

	public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;
        for (char c : s.toCharArray()){
            if (c == '('){
                depth ++;
            }else if (c == ')'){
                depth --;
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }
}
