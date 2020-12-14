package com.problems.palindrome;

/**
 * Given a string s, you can convert it to a palindrome by adding characters in
 * front of it. Find and return the shortest palindrome you can find by
 * performing this transformation. 
 * 
 * Example 1: Input: s = "aacecaaa" Output: "aaacecaaa" 
 * 
 * 
 * IDEA: 
 * 
 * s=abac
 * 
 * str = abac#caba
 * 
 * Using KMP, find repeated symbols for the biggest substr starting from "" to "abac"
 * 
 * It's "aba", then point index to the tail's first symbol
 * 
 *       0 1 2 3 4 5 6 7 8
 *       a b a c # c a b a
 *       | |                 case 2, advance j 
 *       |   |               case 1, advance i,j
 *         |   |             case 3, reset i to the last equals
 *       |        
 * 
 */
public class Solution214 {

    int getLSPindex(char[] s) {
        int m = s.length;
        int twin[] = new int[m];
        twin[0] = 0;
        int i = 0;
        int j = 1;
        while (j < m) {
            if (s[i] == s[j]) {// first character match
                i++;
                twin[j] = i;
                j++;
            } else if (i == 0) {
                twin[j] = 0;
                j++;
            } else {// if chars differ, reset i to the last equal
                i = twin[i - 1];
            }
        }
        return twin[m - 1];
    }

    public String shortestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        String str = s + "#" + new StringBuilder(s).reverse().toString();
        char[] arr = str.toCharArray();
        int index = getLSPindex(arr);
        String val = new StringBuilder(s.substring(index)).reverse().toString() + s;
        return val;
    }

}
