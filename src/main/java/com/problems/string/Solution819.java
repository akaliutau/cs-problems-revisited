package com.problems.string;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
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