package problem.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We are given two sentences A and B. (A sentence is a string of space
 * separated words. Each word consists only of lowercase letters.)
 * 
 * A word is uncommon if it appears exactly once in one of the sentences, and
 * does not appear in the other sentence.
 * 
 * Return a list of all uncommon words.
 * 
 * You may return the list in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: A = "this apple is sweet", B = "this apple is sour" Output:
 * ["sweet","sour"]
 * 
 * IDEA:
 * 1. count the occurrence of every word in string
 * 2. words with count=1 are the answer
 * 
 * 
 */
public class Solution884 {

	public String[] uncommonFromSentences(String a, String b) {
        Map<String, Integer> count = new HashMap<>();
        for (String word: a.split(" ")) {
        	count.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }
        for (String word: b.split(" ")) {
        	count.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }

        List<String> ans = new ArrayList<>();
        for (String word: count.keySet()) {
            if (count.get(word) == 1) {
                ans.add(word);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}
