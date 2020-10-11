package com.problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Design
 */
public class Solution211 {

	class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		boolean word = false;

		public TrieNode() {

		}
	}

	class WordDictionary {
		TrieNode trie;

		/** Initialize your data structure here. */
		public WordDictionary() {
			trie = new TrieNode();
		}

		/** Adds a word into the data structure. */
		public void addWord(String word) {
			TrieNode node = trie;

			for (char ch : word.toCharArray()) {
				if (!node.children.containsKey(ch)) {
					node.children.put(ch, new TrieNode());
				}
				node = node.children.get(ch);
			}
			node.word = true;
		}

		/** Returns if the word is in the node. */
		public boolean searchInNode(char[] word, int from, TrieNode node) {
			for (int i = from; i < word.length; ++i) {
				if (!node.children.containsKey(word[i])) {
					// if the current character is '.'
					// check all possible nodes at this level
					if (word[i] == '.') {
						for (char x : node.children.keySet()) {
							TrieNode child = node.children.get(x);
							if (searchInNode(word, i + 1, child)) {
								return true;
							}
						}
					}
					// if no nodes lead to answer
					// or the current character != '.'
					return false;
				} else {
					// if the character is found
					// go down to the next level in trie
					node = node.children.get(word[i]);
				}
			}
			return node.word;
		}

		/**
		 * Returns if the word is in the data structure. A word could contain the dot
		 * character '.' to represent any one letter.
		 */
		public boolean search(String word) {
			return searchInNode(word.toCharArray(), 0, trie);
		}
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
