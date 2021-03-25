package com.problems.palindrome;

/**
 * You are given two strings a and b of the same length. Choose an index and
 * split both strings at the same index, splitting a into two strings: aprefix
 * and asuffix where a = aprefix + asuffix, and splitting b into two strings:
 * bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix
 * or bprefix + asuffix forms a palindrome.
 * 
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix
 * is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" +
 * "bc", "ab" + "c" , and "abc" + "" are valid splits.
 * 
 * Return true if it is possible to form a palindrome string, otherwise return
 * false.
 * 
 * Notice that x + y denotes the concatenation of strings x and y.
 * 
 * Example 1:
 * 
 * Input: a = "x", b = "y" Output: true
 * 
 * Explaination: If either a or b are palindromes the answer is true since you
 * can split in the following way: aprefix = "", asuffix = "x" bprefix = "",
 * bsuffix = "y" Then, aprefix + bsuffix = "" + "y" = "y", which is a
 * palindrome.
 * 
 * Example 2:
 * 
 * Input: a = "abdef", b = "fecab" Output: true
 * 
 * 
 * 
 * 
 * IDEA: compare abcde with reverseOf(edcda)
 * 
 * strB = a|bcde
 * 
 * 
 * strA = a|dcd|e 
 * 		   |   |
 *       cut1  cut2
 * 
 * NOTE1: situation is symmetric because both strings are split at the same index
 * NOTE2: 
 * 
 */
public class Solution1616 {

	/**
	 * 	returns true is sequences a & b are equal from points a_l and b_r
	 */
	boolean equals(char[] a, char[] b, int l, int r) {
		while (l >= 0 && r < a.length) {
			if (a[l] != b[r]) {
				return false;
			}
			l--;
			r++;
		}
		return true;
	}

	boolean cp(String strA, String strB) {
		int n = strA.length();
		int l = (n - 1) / 2;
		int r = n / 2;
		char[] a = strA.toCharArray();
		char[] b = strB.toCharArray();
        boolean poli = true;

        // edge case 1: one of the string is a polindrome
		while (l >= 0 && r < n) {
			if (a[l] != a[r]) {
				poli = false;
				break;
			}
			l--;
			r++;
		}
		if (poli) {
			return true;
		}
		// standard case: interval [l,r] must be a palindrome
		return equals(a, b, l, r) || equals(b, a, l, r);
	}

	public boolean checkPalindromeFormation(String a, String b) {
		return cp(a, b) || cp(b, a);
	}

}
