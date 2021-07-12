package problem.string;

import java.util.Arrays;

/**
 * Given two strings s and t, determine if they are isomorphic. Two strings are
 * isomorphic if the characters in s can be replaced by t. All occurrences
 * of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a
 * character may map to itself. 
 * 
 * Example 1: Input: s = "egg", t = "add" Output: true
 * 
 * IDEA:
 *  use 2 maps for source and dest
 *  1) use mapping: src => dest to dynamically build map
 *  2) use mapped map for destination letters to cover scenario a => c, b => c
 */
public class Solution205 {

	public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        int[] mapped = new int[256];
        Arrays.fill(mapped, -1);
        boolean[] usedChars = new boolean[256];
        for (int i = 0; i < n; i++){
            int sChar = s.charAt(i);
            int tChar = t.charAt(i);
            if (mapped[sChar] == -1){
                if (usedChars[tChar]){
                    return false;
                }
                mapped[sChar] = tChar;
                usedChars[tChar] = true;
            }else{
                if (mapped[sChar] != tChar){
                    return false;
                }
            }
        }
        return true;
    }

}
