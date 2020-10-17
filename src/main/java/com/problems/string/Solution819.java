package com.problems.string;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a paragraph and a list of banned words, return the most frequent word
 * that is not in the list of banned words. It is guaranteed there is at least
 * one word that isn't banned, and that the answer is unique.
 * 
 * Words in the list of banned words are given in lowercase, and free of
 * punctuation. Words in the paragraph are not case sensitive. The answer is in
 * lowercase.
 * 
 * 
 * 
 * Example:
 * 
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"] Output: "ball" Explanation: "hit" occurs 3 times, but it is
 * a banned word. "ball" occurs twice (and no other word does), so it is the
 * most frequent non-banned word in the paragraph. Note that words in the
 * paragraph are not case sensitive, that punctuation is ignored (even if
 * adjacent to words, such as "ball,"), and that "hit" isn't the answer even
 * though it occurs more because it is banned.
 * 
 * 
 */
public class Solution819 {
	static class Word {
		public String w;
		public int freq = 0;

		public Word(String w) {
			this.w = w.trim().toLowerCase();
		}

	}

	public String mostCommonWord(String paragraph, String[] banned) {
		paragraph = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();
		String[] words = paragraph.split("\\s+");

		Map<String, Word> wordMap = new HashMap<>();
		Set<String> banSet = new HashSet<>();
		for (String s : banned) {
			banSet.add(s.trim().toLowerCase());
		}

		for (String s : words) {
			if (s.isEmpty()) {
				continue;
			}
			Word word = new Word(s);
			if (!banSet.contains(word.w)) {
				if (!wordMap.containsKey(word.w)) {
					wordMap.put(word.w, word);
				}
				wordMap.get(word.w).freq++;
			}
		}
		Comparator<Word> byFreq = (o, p) -> Integer.compare(o.freq, p.freq);
		return wordMap.isEmpty() ? "" : Collections.max(wordMap.values(), byFreq).w;

	}

}