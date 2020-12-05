package com.problems.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DP 
 * 
 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output: [ "cats and dog", "cat sand dog" ]
 * 
 * 
 */
public class Solution140a {

    class Solution {
        protected Set<String> wordSet;

        private void updateCharSet(String s, HashSet<Character> charSet) {
            for (int i = 0; i < s.length(); ++i)
                charSet.add(s.charAt(i));
        }

        public List<String> wordBreak(String s, List<String> wordDict) {
            HashSet<Character> charSet = new HashSet<>();
            updateCharSet(s, charSet);

            HashSet<Character> wordCharSet = new HashSet<>();
            wordSet = new HashSet<>();
            for (String word : wordDict) {
                wordSet.add(word);
                updateCharSet(word, wordCharSet);
            }

            // quick check on the sets of characters
            if (!wordCharSet.containsAll(charSet))
                return new ArrayList<>();

            int len = s.length();
            List<List<List<Integer>>> dp = new ArrayList<>(len + 1);

            for (int i = 0; i < len + 1; ++i) {
                List<List<Integer>> emptyList = new ArrayList<>();
                dp.add(emptyList);
            }
            List<Integer> starts = new ArrayList<>();
            starts.add(0);
            dp.get(0).add(starts);

            for (int end = 1; end < len + 1; ++end) {
                List<List<Integer>> cuts = new ArrayList<>();

                // fill up the values in the dp array.
                for (int start = 0; start < end; ++start) {
                    String word = s.substring(start, end);
                    if (wordSet.contains(word)) {
                        for (List<Integer> subsentence : dp.get(start)) {
                            ArrayList<Integer> copy = new ArrayList<>(subsentence);
                            copy.add(end);
                            cuts.add(copy);
                        }
                    }
                }
                dp.set(end, cuts);
            }

            // reconstruct the words list from the positions of breaks/cuts
            ArrayList<String> ret = new ArrayList<>();
            for (List<Integer> cuts : dp.get(s.length())) {
                StringBuffer sentence = new StringBuffer();
                for (int i = 0; i < cuts.size() - 1; ++i) {
                    sentence.append(s.substring(cuts.get(i), cuts.get(i + 1)) + " ");
                }
                ret.add(sentence.toString().trim());
            }

            return ret;
        }
    }

 

}
