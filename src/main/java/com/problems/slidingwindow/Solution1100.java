package com.problems.slidingwindow;

/**
 * 
 * Sliding window
 * 
 * Given a string S, return the number of substrings of length K with no
 * repeated characters.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: S = "havefunonleetcode", K = 5 Output: 6 Explanation: There are 6
 * substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
 * 
 * 
 * counter havefunonleetcode
 * 
 * aaabac
 *    |||
 *    123
 * 
 * 0) a:3 unique=0
 * 
 * 1) a:2 b:1 unique=1
 * 
 * 2) a:2 b:1 unique=1
 * 
 * 3) a:2 b:1 c:1 unique=1+2=3
 * 
 */
public class Solution1100 {

	public int numKLenSubstrNoRepeats(String s, int k) {
		if (s.length() < k) {
			return 0;
		}

		int unique = 0;
		int countK = 0;
		int[] counter = new int[26];
		char[] letters = s.toCharArray();

		for (int i = 0; i < letters.length; i++) {
			// analyse current letter
			char c = letters[i];
			if (counter[c - 'a'] == 0) {// find unique
				unique++;
			} else if (counter[c - 'a'] == 1) {// becomes duplicate
				unique--;
			} else {// do nothing, because c already has been counted (it was 0 OR 1) 
			    
			}
			counter[c - 'a']++;

			// analyse the block [i, i+k]
			// update statistics starting from the first letter which left this block
			if (i >= k) {
				char d = letters[i - k];// letter about to leave block
				if (counter[d - 'a'] == 2) {// this letter will become unique in block
					unique++;
				} else if (counter[d - 'a'] == 1) {// this letter will absent in block
					unique--;
				}
				counter[d - 'a']--;// update stat
			}
			// increment counter only if all letters are unique in block [i,i+k]
			// intersections are allowed
			if (unique == k) {
				countK++;
			}
		}

		return countK;
	}



}
