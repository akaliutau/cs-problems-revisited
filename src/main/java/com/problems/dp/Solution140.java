package com.problems.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DP 
 * 
 * 
 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output: [ "cats and dog", "cat sand dog" ] 
 * 
 * catsanddog
 *    |   |
 *  start end = 7  
 * dp flow:
 * 
 * dp: [
 * idx list
 * 0   [""],
 * 1   [],
 * 2   [],
 * 3   ["cat"],
 * 4   ["cats"],
 * 5   [],
 * 6   [],
 * 7   ["cat sand","cats and"],
 * 8   [],
 * 9   [],
 * 10  []
 * ]
 * 
 * 
 */
public class Solution140 {

    class Solution {
        protected Set<String> wordSet;

        private void updateCharSet(String s, Set<Character> charSet) {
            for (int i = 0; i < s.length(); ++i)
                charSet.add(s.charAt(i));
        }

        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<Character> stringCharSet = new HashSet<>();
            updateCharSet(s, stringCharSet);

            Set<Character> wordCharSet = new HashSet<>();
            wordSet = new HashSet<>();
            for (String word : wordDict) {
                wordSet.add(word);
                updateCharSet(word, wordCharSet);
            }

            // quick check on the sets of characters
            if (!wordCharSet.containsAll(stringCharSet)) {
                return new ArrayList<>();
            }

            int len = s.length();
            // contains a list of all possible cuts for [0,l)
            List<List<String>> dp = new ArrayList<>(len + 1);
            
            for (int i = 0; i < len + 1; ++i) {
                List<String> emptyList = new ArrayList<>();
                dp.add(emptyList);
            }
            dp.get(0).add("");

            for (int end = 1; end < len + 1; ++end) {
                List<String> sublist = new ArrayList<>();

                // fill up the values in the dp array.
                for (int start = 0; start < end; ++start) {
                    String word = s.substring(start, end);
                    if (wordSet.contains(word)) {
                        for (String subsentence : dp.get(start)) {
                            sublist.add((subsentence + " " + word).trim());
                        }
                    }
                }
                dp.set(end, sublist);
            }

            return dp.get(len);
        }
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
