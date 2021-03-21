package com.problems.string;

import java.util.HashSet;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings
 * of S that occur 2 or more times. (The occurrences may overlap.)
 * 
 * Return any duplicated substring that has the longest possible length. (If S
 * does not have a duplicated substring, the answer is "".)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "banana" Output: "ana" Example 2:
 * 
 * Input: "abcd" Output: ""
 * 
 * IDEA:
 * 
 * 
 */
public class Solution1044 {
	
	// modulus value for the rolling hash function to avoid overflow
	final long modulus = (long) Math.pow(2, 32);


	/*
	 * Rabin-Karp with polynomial rolling hash. Search a substring of given length
	 * that occurs at least 2 times. Return start position if the substring exits
	 * and -1 otherwise.
	 */
	int search(int l, int a, int n, int[] nums) {
		// compute the hash of string S[:l]
		long h = 0;
		for (int i = 0; i < l; ++i) {
			h = (h * a + nums[i]) % modulus;
		}

		// already seen hashes of strings of length l
		HashSet<Long> seen = new HashSet<>();
		seen.add(h);
		// const value to be used often : a**l % modulus
		long al = 1;
		for (int i = 1; i <= l; ++i) {
			al = (al * a) % modulus;
		}
		
		for (int start = 1; start < n - l + 1; ++start) {
			// compute rolling hash in O(1) time
			h = (h * a - nums[start - 1] * al % modulus + modulus) % modulus;
			h = (h + nums[start + l - 1]) % modulus;
			if (seen.contains(h))
				return start;
			seen.add(h);
		}
		return -1;
	}

	public String longestDupSubstring(String s) {
		int n = s.length();

		int[] nums = new int[n];
		for (int i = 0; i < n; ++i) {
			nums[i] = s.charAt(i) -'a';
		}
		// base value for the rolling hash function
		int a = 26;

		// binary search, l = repeating string length
		int left = 1, right = n;
		int l;
		while (left <= right) {
			l = left + (right - left) / 2;
			if (search(l, a, n, nums) != -1) {
				left = l + 1;
			}else {
				right = l - 1;
			}
		}

		int start = search(left - 1, a, n, nums);
		return s.substring(start, start + left - 1);
	}

	

}
