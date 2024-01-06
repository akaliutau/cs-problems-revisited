package problem.string;

import java.util.Arrays;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 * 
 * If the length of the string is 1, stop. If the length of the string is > 1,
 * do the following: Split the string into two non-empty substrings at a random
 * index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order.
 * i.e., after this step, s may become s = x + y or s = y + x. Apply step 1
 * recursively on each of the two substrings x and y. Given two strings s1 and
 * s2 of the same length, return true if s2 is a scrambled string of s1,
 * otherwise, return false.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s1 = "great", s2 = "rgeat" Output: true
 * 
 * IDEA:
 * generate all possible variants
 * for speed up comparison of final parts use letter distribution calculations
 * 
 *  original         referenced
 * -----------      ------------
 * a_1 a_2 a_3      b_1 b_2 b_3
 * 
 * on each iteration check 2 mapping variants:
 * a_1|a_2 a_3      b_1 -> a_1 OR b_2 b_3 -> a_2 a_3
 * 
 * a_1 a_2 | a_3    b_1 b_2 -> a_1 a_2 OR b_3 -> a_3
 * 
 * 
 * 
 * complexity: O(n^2)
 * 
 * 
 */
public class Solution87 {
	
	static int[] getDistr(String s) {
		int[] d = new int[26];
		for (char c : s.toCharArray()) {
			d[c-'a'] ++;
		}
		return d;
	}
	
	
	static boolean isEligible(String str1, String ref) {
		return Arrays.equals(getDistr(str1), getDistr(ref));
	}
	
	
	static boolean scrambled(String str1, String ref) {
		if (!isEligible(str1, ref)) {
			return false;
		}
		if (str1.equals(ref)) {
			return true;
		}
		int n = str1.length();
		for (int i = 1; i < n; i++) {
			String part1 = str1.substring(0, i);
			String part2 = str1.substring(i, n);
			String ref1 = ref.substring(0, i);
			String ref2 = ref.substring(i, n);
			String ref1a = ref.substring(0, n - i);
			String ref2a = ref.substring(n - i, n);
			if ((scrambled(part1, ref1) && scrambled(part2, ref2)) ||
                (scrambled(part2, ref1a) && scrambled(part1, ref2a)))  {
				return true;
			}
		}
		return false;
	}

	public boolean isScramble(String s1, String s2) {
		return scrambled(s1,  s2);
	}

}
