package com.problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Design a data structure that supports adding new words and finding if a
 * string matches any previously added string.
 * 
 * Implement the WordDictionary class:
 * 
 * WordDictionary() Initializes the object. void addWord(word) Adds word to the
 * data structure, it can be matched later. bool search(word) Returns true if
 * there is any string in the data structure that matches word or false
 * otherwise. word may contain dots '.' where dots can be matched with any
 * letter.
 * 
 * 
 * Example:
 * 
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]] Output
 * [null,null,null,null,false,true,true,true]
 * 
 * Explanation WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad"); 
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad"); 
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True 
 * wordDictionary.search(".ad"); // return True 
 * wordDictionary.search("b.."); // return True
 * 
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
