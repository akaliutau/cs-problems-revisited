package com.problems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design
 * 
 * 
 */
public class Solution642 {

	static class Node {
		String sentence;
		int times;

		Node(String st, int t) {
			sentence = st;
			times = t;
		}
	}

	class AutocompleteSystem {
		private Map<String, Integer> freq = new HashMap<>();// freq sentence => times
		private String curSentence = "";

		public AutocompleteSystem(String[] sentences, int[] times) {
			for (int i = 0; i < sentences.length; i++) {
				freq.put(sentences[i], times[i]);
			}
		}

		public List<String> input(char c) {
			List<String> res = new ArrayList<>();
			if (c == '#') {
				freq.put(curSentence, freq.getOrDefault(curSentence, 0) + 1);// update statistics and reset 
				curSentence = "";
			} else {
				List<Node> list = new ArrayList<>();
				curSentence += c;
				
				// find candidates
				for (String key : freq.keySet()) {
					if (key.indexOf(curSentence) == 0) {
						list.add(new Node(key, freq.get(key)));
					}
				}
				// get top 3 by frequency
				Comparator<Node> byTimes = (o,p) -> Integer.compare(p.times, o.times);
				Comparator<Node> bySentence = (o,p) -> o.sentence.compareTo(p.sentence);
				Collections.sort(list,byTimes.thenComparing(bySentence));
				
				for (int i = 0; i < Math.min(3, list.size()); i++) {
					res.add(list.get(i).sentence);
				}
			}
			return res;
		}
	}

	/**
	 * Your AutocompleteSystem object will be instantiated and called as such:
	 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
	 * List<String> param_1 = obj.input(c);
	 */

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
