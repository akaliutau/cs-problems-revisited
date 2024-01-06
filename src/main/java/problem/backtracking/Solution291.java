package problem.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string s, return true if s matches the pattern. A
 * string s matches a pattern if there is some bijective mapping of single
 * characters to strings such that if each character in pattern is replaced by
 * the string it maps to, then the resulting string is s. A bijective mapping
 * means that no two characters mapping to the same string, and no character maps to
 * two different strings. 
 * 
 * Example 1: Input: pattern = "abab", s =
 * "redblueredblue" Output: true 
 * 
 * Explanation: One possible mapping is as
 * follows: 'a' -> "red" 'b' -> "blue"
 * 
 * IDEA: 
 * 1) start with smallest cut, then expanding initial string
 * 2) apply this process recursively to the rest part of the string
 * 
 * Example:
 * pattern = aba, s = bluewhiteblue
 * 
 *  
 * 
 */
public class Solution291 {

   Map<Character, String> mapping = new HashMap<>();
   Set<String> seen = new HashSet<>();// 'a' -> "red" 'b' -> "blue"


   boolean backtracking(char[] pattern, int from, int n, String s) {
	   // conditions to exit
       if (n == from && s.length() == 0)
           return true;
       if (n == from || s.length() == 0)
           return false;
       
       // main logic
       char key = pattern[from];
       if (mapping.containsKey(key)) {
           String val = mapping.get(key);
           if (s.startsWith(val)) {
               return backtracking(pattern, from + 1, n, s.substring(val.length()));// if all goes smoothly so far, drop mapped substring and continue with TAIL
           }else {// contradiction
               return false;
           }
       } else {// try all possible substrings starting from 0 till the end of string
           int patternLen = n - from;
           int max = s.length() - patternLen + 1;
           for (int i = 1; i <= max; i++) {// max + patternLen = s.length() - 1 - [opt, could be till the end]
               String mappedVal = s.substring(0, i);
               if (seen.contains(mappedVal))// already checked
                   continue;
               seen.add(mappedVal);
               mapping.put(key, mappedVal);
               // if was able to go through
               if (backtracking(pattern, from + 1, n, s.substring(i)))// omit 1 symbol at pattern and [0,i] block of chars on string
                   return true;
               mapping.remove(key);// backtracking, remove mapping for key
               seen.remove(mappedVal);
           }
           // nothing matches
           return false;
       }
   }
   
   public boolean wordPatternMatch(String pattern, String s) {
       return backtracking(pattern.toCharArray(), 0, pattern.length(), s);
   }

}
