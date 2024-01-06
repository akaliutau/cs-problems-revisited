package problem.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and an array of strings words, return the number of words[i]
 * that is a subsequence of s.
 * 
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative
 * order of the remaining characters.
 * 
 * For example, "ace" is a subsequence of "abcde".
 * 
 * 
 * Example 1:
 * 
 * Input: s = "abcde", words = ["a","bb","acd","ace"] Output: 3
 * 
 * Explanation: There are three strings in words that are a subsequence of s:
 * "a", "acd", "ace".
 * 
 * IDEA:
 * 
 * 1. create a pointer structure (Word) which contains the desired symbol to find
 * 2. use a set of pointers for each word and update this set for each found symbol
 *    simultaneously for all words
 * 
 * f.e. for words:
 * 
 * "abcdabe"
 * ["abe", "dd"]
 * 
 * 
 * 
 * 
 */
public class Solution792 {
	
  	static class Word {
		char[] word;
		int index;

		public Word(String w, int i) {
			word = w.toCharArray();
			index = i;
		}
		
		public char next() {
			return word[index];
		}
	}

	public int numMatchingSubseq(String s, String[] words) {
		int ans = 0;
		Map<Character, List<Word>> qNeededSymbols = new HashMap<>();// dynamic list of words found so far with specific character

		// initiate the seeds
		for (String word : words)
			qNeededSymbols.computeIfAbsent(word.charAt(0), k -> new ArrayList<>()).add(new Word(word, 0));

		for (char c : s.toCharArray()) {                   // iterate through each character in string
			List<Word> queued = qNeededSymbols.remove(c);  // list of words which requested specific character
            if (queued != null)
            	for (Word word : queued) {
            		word.index++;
            		if (word.index == word.word.length) {
            			ans++;
            		} else {// compute next wished symbol for this word (tracked by Word) 
            			qNeededSymbols.computeIfAbsent(word.next(), k -> new ArrayList<>()).add(word);
            		}
            	}
		}
		return ans;
	}

}
