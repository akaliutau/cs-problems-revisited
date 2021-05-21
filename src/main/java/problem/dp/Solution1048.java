package problem.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of words, each word consists of English lowercase letters.
 * 
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly
 * one letter anywhere in word1 to make it equal to word2. For example, "abc" is
 * a predecessor of "abac".
 * 
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >=
 * 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of
 * word_3, and so on.
 * 
 * Return the longest possible length of a word chain with words chosen from the
 * given list of words.
 * 
 * Example 1:
 * 
 * Input: words = ["a","b","ba","bca","bda","bdca"] Output: 4 
 * 
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 * 
 * IDEA:
 * 
 * 
 * 
 */
public class Solution1048 {
	
	public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();

        Arrays.sort(words, (o, p) -> o.length() - p.length());

        int longestSeqLength = 1;

        for (String word : words) {
            int curSeqLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) {
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString();
                int previousSeqLength = dp.getOrDefault(predecessor, 0);
                curSeqLength = Math.max(curSeqLength, previousSeqLength + 1);
            }
            dp.put(word, curSeqLength);
            longestSeqLength = Math.max(longestSeqLength, curSeqLength);
        }
        return longestSeqLength;
    }
}
