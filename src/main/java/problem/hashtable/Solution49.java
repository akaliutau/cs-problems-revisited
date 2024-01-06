package problem.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the anagrams together. You can return
 * the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * 
 * Example 1:
 * 
 * Input: strs = ["eat","tea","tan","ate","nat","bat"] Output:
 * [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * IDEA:
 * use footprints (statistical representation) for appropriate words - see impl of class FootPrint 
 * 
 */
public class Solution49 {

	public static class FootPrint {
		public int[] chars = new int[26];

		public FootPrint(String s) {
			int n = s.length();
			for (int i = 0; i < n; i++) {
				chars[(int) (s.charAt(i) - 'a')]++;
			}
		}

		@Override
		public boolean equals(Object o) {
			FootPrint fp = (FootPrint) o;
			return Arrays.equals(fp.chars, chars);
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(chars);
		}
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> results = new ArrayList<>();
		if (strs.length == 0) {
			return results;
		}
		Map<FootPrint, List<String>> groupped = new HashMap<>();
		for (String s : strs) {
			FootPrint fp = new FootPrint(s);
			groupped.computeIfAbsent(fp, k -> new ArrayList<>()).add(s);
		}

		results.addAll(groupped.values());
		return results;

	}



}
