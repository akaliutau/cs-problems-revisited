package problem.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 * 
 * Symbol Value 
 * I 1 
 * V 5 
 * X 10 
 * L 50 
 * C 100 
 * D 500 
 * M 1000 
 * 
 * For example, 2 is written
 * as II in Roman numeral, just two one's added together. 12 is written as XII,
 * which is simply X + II. The number 27 is written as XXVII, which is XX + V +
 * II.
 * 
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There
 * are six instances where subtraction is used:
 * 
 * I can be placed before V (5) and X (10) to make 4 and 9. X can be placed
 * before L (50) and C (100) to make 40 and 90. C can be placed before D (500)
 * and M (1000) to make 400 and 900. Given a roman numeral, convert it to an
 * integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "III" Output: 3
 * 
 * IDEA:
 * cover special cases first, and add normal mapping if not the case
 * 
 */
public class Solution10 {
	
	
	   public int romanToInt(String s) {
			Map<Character, Integer> map = new HashMap<>();
			map.put('I', 1);
			map.put('V', 5);
			map.put('X', 10);
			map.put('L', 50);
			map.put('C', 100);
			map.put('D', 500);
			map.put('M', 1000);

			int n = s.length();

			char[] r = s.toCharArray();

			int integer = 0;

			char[] dp = new char[2];

			int i = 0;
			boolean doubleP = false;
			while (i < n) {
				if (i + 1 < n) {
					doubleP = true;
					dp[1] = r[i];
					dp[0] = r[i + 1];
				} else {
					doubleP = false;
				}
				if (doubleP) {
					if (dp[0] == 'V' && dp[1] == 'I') {
						i += 2;
						integer += 4;
					} else if (dp[0] == 'X' && dp[1] == 'I') {
						i += 2;
						integer += 9;
					} else if (dp[0] == 'L' && dp[1] == 'X') {
						i += 2;
						integer += 40;
					} else if (dp[0] == 'C' && dp[1] == 'X') {
						i += 2;
						integer += 90;
					} else if (dp[0] == 'D' && dp[1] == 'C') {
						i += 2;
						integer += 400;
					} else if (dp[0] == 'M' && dp[1] == 'C') {
						i += 2;
						integer += 900;
					} else {
						integer += map.get(r[i]);
						i++;
					}
				} else {
					integer += map.get(r[i]);
					i++;
				}
			}

			return integer;
	        
	    }

}
