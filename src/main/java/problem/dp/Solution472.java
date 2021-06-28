package problem.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a list of words (without duplicates), write a program that
 * returns all concatenated words in the given list of words. A concatenated
 * word is defined as a string that is comprised entirely of at least two
 * shorter words in the given array.
 * 
 * Examples: 
 * Input:
 * ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; "ratcatdogcat"
 * can be concatenated by "rat", "cat", "dog" and "cat".
 * 
 * Input: ["cat","cats","catsdogcats","dog" Output: ["catsdogcats","dogcatsdog"]
 * 
 * IDEA:
 * we are cutting a prefix AND suffix at each level, filtering out all invalid variants
 * if both  prefix AND suffix are valid, we are adding the initial word as a complex one
 * 
 * Let m = avr length of word
 * Len n = # of words
 * 
 * 1st iteration - m cycles
 * 2nd iteration - m-1 cycles
 * 
 * m + (m-1) + (m-2) + ... + 1 = m * (m - 1) / 2
 * 
 * 
 * O(n * m^2/2)
 * 
 */
public class Solution472 {

	static boolean checkSuffix(String word, int from, Set<String> hset, List<String> result) {
		String suffix = word.substring(from);// generate a suffix from word
		if (suffix.isEmpty()) {
			return false;
		}

		// Add to the result if last part of the string is found 
		if (from != 0 && hset.contains(suffix)) {// str = catsdogcats - > cats + dogcats - NOT TRUE
			result.add(word);// NOTE: we are adding initial string
			return true;
		}

		for (int i = 1; i <= suffix.length(); i++) {
			String prefix = suffix.substring(0,i);
			// we go further if and only if prefix is a valid word
			if (hset.contains(prefix)) {
				// if suffix is a valid word
				if (checkSuffix(word, from + i, hset, result)) {// try catsdogcats as cats + dog + [cats]
					return true;
				}
			}
		}

		return false;
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Set<String> hset = new HashSet<>();
		for (String s : words) {
			hset.add(s);
		}

		List<String> res = new ArrayList<>();
		for (String s : words) {
			checkSuffix(s, 0, hset, res);
		}

		return res;
	}



}
