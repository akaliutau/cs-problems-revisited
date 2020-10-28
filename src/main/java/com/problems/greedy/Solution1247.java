package com.problems.greedy;

/**
 * You are given two strings s1 and s2 of equal length consisting of letters "x"
 * and "y" only. Your task is to make these two strings equal to each other. You
 * can swap any two characters that belong to different strings, which means:
 * swap s1[i] and s2[j]. Return the minimum number of swaps required to make s1
 * and s2 equal, or return -1 if it is impossible to do so. 
 * 
 * Example 1: Input: s1
 * = "xx", s2 = "yy" Output: 1 
 * 
 * Explanation: Swap s1[0] and s2[1], s1 = "yx", s2
 * = "yx".
 * 
 *           M       M     M 
 *  s1 - X X Y Y X Y X Y X X 
 *       | | | | | | | | | | 
 *  s2 - X Y Y X Y X X X Y X
 *  
 *  
 *   S1 - S2 : (Mis-Matched)
 * 1. X - Y  :     3
 * 2. Y - X  :     3
 * 
 * If total mismatched i.e, X-Y(MisMatched) + Y-X(MisMatched) == odd (Then return -1 no ans is possible)
 */
public class Solution1247 {

    public int minimumSwap(String s1, String s2) {
        int xToy = 0, yTox = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                continue;
            if(s1.charAt(i) == 'x') {
                xToy++;
            }else {
                yTox++;
            }
        }
        if ((xToy + yTox) % 2 == 1) {
            return -1;
        }
        return xToy / 2 + yTox / 2 + (xToy % 2 == 1 ? 2 : 0);// add +1 to each if odd
    }

}
