package com.problems.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * We are given two sentences A and B. (A sentence is a string of space
 * separated words. Each word consists only of lowercase letters.)
 * 
 * A word is uncommon if it appears exactly once in one of the sentences, and
 * does not appear in the other sentence.
 * 
 * Return a list of all uncommon words.
 * 
 * You may return the list in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: A = "this apple is sweet", B = "this apple is sour" Output:
 * ["sweet","sour"]
 * 
 * IDEA:
 * 
 */
public class Solution884 {

	public String[] uncommonFromSentences(String a, String b) {
		Set<String> common = new HashSet<>();

		
		Set<String> setA = new HashSet<>();
		for (String word : a.split(" ")) {
			if (word.length() > 0) {
				if (setA.contains(word)) {
					common.add(word);
				}
				setA.add(word);
			}
		}
		Set<String> setB = new HashSet<>();
		for (String word : b.split(" ")) {
			if (word.length() > 0) {
				if (setB.contains(word)) {
					common.add(word);
				}
				setB.add(word);
			}
		}
		Set<String> result = new HashSet<>();
		for (String word : setB) {
			if (!setA.contains(word)) {
				result.add(word);
			} else {
			    common.add(word);
			}
		}

		result.addAll(setA);
		result.removeAll(common);
		return result.toArray(new String[result.size()]);

	}
}
