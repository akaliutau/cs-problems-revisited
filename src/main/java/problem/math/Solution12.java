package problem.math;

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
 * and M (1000) to make 400 and 900. Given an integer, convert it to a roman
 * numeral.
 * 
 * IDEA: 
 * 1. consider Roman number as a block (container) to hold a specific amount of items
 * 2. define how many times(==cycles) it is necessary to add up the current block (== Roman number), 
 * 3. if initial number to convert is still > 0, then  decrease the index in integers array
 * 
 */
public class Solution12 {

	public String intToRoman(int num) {
		StringBuilder sb = new StringBuilder();
		String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
		int[] integers = new int[]     { 1,    4,    5,    9,  10,   40,   50,  90,  100, 400,  500, 900, 1000 };
		for (int i = integers.length - 1; i >= 0; i--) {
			int times = num / integers[i];
			num %= integers[i];
			while (times-- > 0) {
				sb.append(romans[i]);
			}
		}
		return sb.toString();
	}


}
