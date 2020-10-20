package com.problems.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words, return all the pairs of the distinct indices
 * (i, j) in the given list, so that the concatenation of the two words words[i]
 * + words[j] is a palindrome. 
 * 
 * Example 1: Input: words = ["abcd","dcba","lls","s","sssll"] 
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * 
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 */
public class Solution336 {

    class TrieNode {
        public int wordEnding = -1; // We'll use -1 to mean there's no word ending here.
        public TrieNode[] next = new TrieNode[256];
        public List<Integer> prefixLi = new ArrayList<>();
    }

    // Is the given string a palindrome after index i?
    // Tip: Leave this as a method stub in an interview unless you have time
    // or the interviewer tells you to write it. The Trie itself should be
    // the main focus of your time.
    boolean hasPalindromeRemaining(String s, int i) {
        int p1 = i;
        int p2 = s.length() - 1;
        while (p1 < p2) {
            if (s.charAt(p1) != s.charAt(p2))
                return false;
            p1++;
            p2--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {

        TrieNode trie = new TrieNode();

        // Build the Trie
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            String reversedWord = new StringBuilder(word).reverse().toString();
            TrieNode curLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                if (hasPalindromeRemaining(reversedWord, j)) {
                    curLevel.prefixLi.add(wordId);
                }
                char c = reversedWord.charAt(j);
                if (curLevel.next[c] == null) {
                    curLevel.next[c] = new TrieNode();
                }
                curLevel = curLevel.next[c];
            }
            curLevel.wordEnding = wordId;
        }

        // Find pairs
        List<List<Integer>> pairs = new ArrayList<>();
        for (int wordId = 0; wordId < words.length; wordId++) {
            String word = words[wordId];
            TrieNode curLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                // Check for pairs of case 3.
                if (curLevel.wordEnding != -1 && hasPalindromeRemaining(word, j)) {
                    pairs.add(Arrays.asList(wordId, curLevel.wordEnding));
                }
                // Move down to the next trie level.
                char c = word.charAt(j);
                curLevel = curLevel.next[c];
                if (curLevel == null)
                    break;
            }
            if (curLevel == null)
                continue;
            // Check for pairs of case 1. Note the check to prevent non distinct pairs.
            if (curLevel.wordEnding != -1 && curLevel.wordEnding != wordId) {
                pairs.add(Arrays.asList(wordId, curLevel.wordEnding));
            }
            // Check for pairs of case 2.
            for (int other : curLevel.prefixLi) {
                pairs.add(Arrays.asList(wordId, other));
            }
        }

        return pairs;
    }
}
