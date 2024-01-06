package problem.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a search autocomplete system for a search engine. Users may input a
 * sentence (at least one word and end with a special character '#'). For each
 * character they type except '#', you need to return the top 3 historical hot
 * sentences that have prefix the same as the part of sentence already typed.
 * Here are the specific rules:
 * 
 * The hot degree for a sentence is defined as the number of times a user typed
 * the exactly same sentence before. The returned top 3 hot sentences should be
 * sorted by hot degree (The first is the hottest one). If several sentences
 * have the same degree of hot, you need to use ASCII-code order (smaller one
 * appears first). If less than 3 hot sentences exist, then just return as many
 * as you can. When the input is a special character, it means the sentence
 * ends, and in this case, you need to return an empty dynamicList. Your job is
 * to implement the following functions:
 * 
 * The constructor function:
 * 
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
 * The input is historical data. Sentences is a string array consists of
 * previously typed sentences. Times is the corresponding times a sentence has
 * been typed. Your system should record these historical data.
 * 
 * Now, the user wants to input a new sentence. The following function will
 * provide the next character the user types:
 * 
 * List<String> input(char c): The input c is the next character typed by the
 * user. The character will only be lower-case letters ('a' to 'z'), blank space
 * (' ') or a special character ('#'). Also, the previously typed sentence
 * should be recorded in your system. The output will be the top 3 historical
 * hot sentences that have prefix the same as the part of sentence already
 * typed.
 * 
 * 
 * Example: Operation: AutocompleteSystem(["i love you", "island","ironman", "i
 * love leetcode"], [5,3,2,2]) The system have already tracked down the
 * following sentences and their corresponding times: "i love you" : 5 times
 * "island" : 3 times "ironman" : 2 times "i love leetcode" : 2 times Now, the
 * user begins another search:
 * 
 * Operation: input('i') Output: ["i love you", "island","i love leetcode"]
 * Explanation: There are four sentences that have prefix "i". Among them,
 * "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII
 * code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of
 * "ironman". Also we only need to output top 3 hot sentences, so "ironman" will
 * be ignored.
 * 
 * Operation: input(' ') Output: ["i love you","i love leetcode"] Explanation:
 * There are only two sentences that have prefix "i ".
 * 
 * Operation: input('a') Output: [] Explanation: There are no sentences that
 * have prefix "i a".
 * 
 * Operation: input('#') Output: [] Explanation: The user finished the input,
 * the sentence "i a" should be saved as a historical sentence in system. And
 * the following input will be counted as a new search.
 * 
 * IDEA:
 * 
 * 
 */
public class Solution642 {

	static class Node {
		String sentence;
		int idx;
		int times;

		Node(String st, int id, int t) {
			sentence = st;
			idx = id;
			times = t;
		}
	}

	class AutocompleteSystem {
		private Map<String, Node> freq = new HashMap<>();// freq sentence => times
		private String curSentence = "";
		int idx = 0;
		// get top 3 by frequency
		Comparator<Node> byTimes = (o, p) -> o.times - p.times;
		Comparator<Node> bySentence = (o, p) -> p.sentence.compareTo(o.sentence);

		public AutocompleteSystem(String[] sentences, int[] times) {
			for (int i = 0; i < sentences.length; i++) {
				freq.put(sentences[i], new Node(sentences[i], i, times[i]));
				idx++;
			}
		}

		public List<String> input(char c) {
			List<Node> res = new ArrayList<>();
			if (c == '#') {// user clicked search again
				if (freq.containsKey(curSentence)) {
					freq.get(curSentence).times++;
				} else {
					freq.put(curSentence, new Node(curSentence, idx++, 1));
				}
				curSentence = "";
			} else {
				curSentence += c;

				PriorityQueue<Node> dynamicList = new PriorityQueue<>(byTimes.thenComparing(bySentence));
				// find candidates
				for (String key : freq.keySet()) {
					if (key.startsWith(curSentence)) {
						dynamicList.add(freq.get(key));// note: just for convenience
					}
					if (dynamicList.size() > 3) {
						dynamicList.poll();
					}
				}

				res.addAll(dynamicList);
			}
			Comparator<Node> byTimesRev = (o, p) -> p.times - o.times;
			Comparator<Node> bySentenceNat = (o, p) -> o.sentence.compareTo(p.sentence);
			Collections.sort(res, byTimesRev.thenComparing(bySentenceNat));
			List<String> ret = new ArrayList<>();
			res.forEach(node -> 
				ret.add(node.sentence)
			);

			return ret;
		}
	}

	/**
	 * Your AutocompleteSystem object will be instantiated and called as such:
	 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
	 * List<String> param_1 = obj.input(c);
	 */

}
