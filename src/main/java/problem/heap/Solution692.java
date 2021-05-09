package problem.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 * 
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1: Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"] Explanation: "i" and "love" are the two most frequent
 * words. Note that "i" comes before "love" due to a lower alphabetical order.
 * 
 * 
 */
public class Solution692 {
	static class Elem {
		public int freq = 0;
		public String word;

		public Elem(String word) {
			this.word = word;
		}

	}

	public List<String> topKFrequent(String[] words, int k) {
		int n = words.length;
		Comparator<Elem> byFreq = (o, p) -> Integer.compare(p.freq, o.freq);
		Comparator<Elem> byWord = (o, p) -> o.word.compareToIgnoreCase(p.word);
		PriorityQueue<Elem> queue = new PriorityQueue<>(n, byFreq.thenComparing(byWord));
		Map<String, Elem> map = new HashMap<>();
		for (String word : words) {
			if (!map.containsKey(word)) {
				map.put(word, new Elem(word));
			}
			map.get(word).freq++;
		}
		queue.addAll(map.values());

		List<String> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			res.add(queue.poll().word);
		}
		return res;

	}

}
