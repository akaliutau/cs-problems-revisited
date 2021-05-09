package problem.string;

import java.util.Stack;

/**
 * 
 * Given a string s, remove duplicate letters so that every letter appears once and only once. 
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * 
 * Example 1:
 *
 * Input: s = "bcabc"
 * Output: "abc"
 * 
 * IDEA:
 * use freq map, iterate through array from back, removing duplicates on the way
 * 
 * aswddefghjsd
 *  | ||     ||
 *  |        |
 *           |
 *          will be added after frequency nullification
 *  
 *  
 * a        taken=[a]        a is single, will never be touched again (while loop stops)
 * as       taken=[a,s]      s has a frequency 2, will be added one time NOTE: if a had freq > 1, then astua: astu < stua
 * asw      taken=[a,s,w]    w is single, will never be touched again (while loop stops)
 * aswd     taken=[a,s,w,d]
 * aswde    taken=[a,s,w,d,e]
 *  
 * complexity O(2n)
 * 
 */
public class Solution1081 {

	public String removeDuplicateLetters(String s) {
		int[] freq = new int[26];
		boolean[] taken = new boolean[26];
		
		char[] letters = s.toCharArray();

		// create a frequency map 
		// each letter => how many time it has been seen
		for (char c : letters) {
			freq[c - 'a']++;
		}

		Stack<Character> stack = new Stack<>();
		stack.add('0');
		for (char c : letters) {
			freq[c - 'a']--;
			if (!taken[c - 'a']) {// if letter c is not taken yet on previous iteration removing duplicates on the way
				// used to clean up stack:
				// remove all letters which have frequency > 0 (i.e. duplicates) AND lexicographically bigger than cur
				// i.e  ed[a] <--  e and d will be removed, and this will be repeated as much as frequencies for e & d > 0
				while (stack.peek() > c && freq[stack.peek() - 'a'] > 0) {
					taken[stack.peek() - 'a'] = false;
					stack.pop();
				}
				stack.add(c);
				taken[c - 'a'] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < stack.size(); i++) {
			sb.append(stack.get(i));
		}
		return sb.toString();

	}

	

}
