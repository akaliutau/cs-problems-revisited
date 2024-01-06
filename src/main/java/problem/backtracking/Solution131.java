package problem.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome. Return all possible palindrome partitioning of s. 
 * 
 * Example: Input:
 * "aab" Output: [ ["aa","b"], ["a","a","b"] ]
 * 
 * IDEA: 
 * 1) start with smallest cut, then expanding initial string
 * 2) apply this process recursively to the rest part of the string
 * 
 * Example:
 * aba = 
 *    a -> ba = a -> b - >a (when the index is out of boundary, add the tuple (a, b, a)) note, the strings structure will be updated for 3 cuts, specifically for parts aadded
 *    ab - dropped, because a!=b
 *    aba - added as a whole string, using info about internal part (string "b")
 *    
 * 
 * 
 * 
 */
public class Solution131 {

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] strings) {
        if (start >= s.length()) {
            result.add(new ArrayList<>(currentList));
        }
        // compose all strings of [start, start], [start, start + 1], ... , [start, end]
        for (int end = start; end < s.length(); end++) {
        	// conditions for palindrome:
        	// 1)  edge letters are the same     +   2) the length = 2 (f.e. [aa]) or inner string is palindrome 
        	if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || strings[start + 1][end - 1])) {
            	strings[start][end] = true;// add it to auxiliary structure
                currentList.add(s.substring(start, end + 1));// add candidate
                dfs(result, s, end + 1, currentList, strings);// cut next part starting from [end+1]
                currentList.remove(currentList.size() - 1);// remove last added - backtracking
            }
        }
    }

    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

}
