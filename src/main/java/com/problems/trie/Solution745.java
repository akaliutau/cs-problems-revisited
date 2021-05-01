package com.problems.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Design a special dictionary which has some words and allows you to search the
 * words in it by a prefix and a suffix.
 * 
 * Implement the WordFilter class:
 * 
 * WordFilter(string[] words) Initializes the object with the words in the
 * dictionary. f(string prefix, string suffix) Returns the index of the word in
 * the dictionary which has the prefix prefix and the suffix suffix. If there is
 * more than one valid index, return the largest of them. If there is no such
 * word in the dictionary, return -1.
 * 
 * 
 * Example 1:
 * 
 * Input ["WordFilter", "f"] [[["apple"]], ["a", "e"]] Output [null, 0]
 * 
 * Explanation WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix =
 * "a" and suffix = 'e".
 * 
 * IDEA:
 * 1) Maintain 2 tries: for prefixes and the 2nd one - for the reverse suffixes
 * 2) for each word remember the sorted list of matched words
 * 3) find the biggest number in intersection
 * 
 */
public class Solution745 {

	static class Node {
		public char val;
		public Set<Integer> idx = new HashSet<>();
		public Map<Character, Node> children = new HashMap<>();
		public int[] sorted;

		public Node(char c) {
			val = c;
		}

		public void update() {
			List<Integer> indecies = new ArrayList<>(idx);
			indecies.sort((o, p) -> o - p);
			sorted = new int[indecies.size()];
			int i = 0;
			for (int idx : indecies) {
				sorted[i++] = idx;
			}
		}
	}

	static class Trie {
		public Node root = new Node(' ');

		public void add(String word, int idx) {
			Node cur = this.root;
			for (char c : word.toCharArray()) {
				cur = cur.children.computeIfAbsent(c, k -> new Node(k));
				cur.idx.add(idx);
			}
			cur.idx.add(idx);
		}

		public int[] getIndex(String word) {
			Node cur = this.root;
			for (char c : word.toCharArray()) {
				if (!cur.children.containsKey(c)) {
					return null;
				}
				cur = cur.children.get(c);
			}
			return cur.sorted;
		}

		public void update(Node node) {
			if (node.sorted == null) {
				node.update();
			}
			for (Node n : node.children.values()) {
				update(n);
			}
		}
	}

	static class WordFilter {

		private final Trie prefixTrie = new Trie();
		private final Trie suffixTrie = new Trie();

		public WordFilter(String[] words) {
			for (int i = 0; i < words.length; i++) {
				prefixTrie.add(words[i], i);
				suffixTrie.add(new StringBuilder(words[i]).reverse().toString(), i);
			}
			prefixTrie.update(prefixTrie.root);
			suffixTrie.update(suffixTrie.root);
		}

		public int f(String prefix, String suffix) {
			int idx = -1;

			int[] arrP = prefixTrie.getIndex(prefix);
			if (arrP != null && arrP.length > 0) {
				int[] arrS = suffixTrie.getIndex(new StringBuilder(suffix).reverse().toString());
				if (arrS != null && arrS.length > 0) {
					return common(arrP, arrS);
				}
			}
			return idx;
		}
		
		public int common(int[] left, int[] right) {
			int n = left.length - 1;
			int m = right.length - 1;
			while (n >= 0 && m >= 0) {
				if (left[n] == right[m]) {
					return left[n];
				}else if (left[n] > right[m]) {
					n --;
				}else if (left[n] < right[m]) {
					m --;
				}
			}
			return -1;
		}
	}

}
