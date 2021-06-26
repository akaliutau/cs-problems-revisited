package problem.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * add spaces in s to construct a sentence where each word is a valid dictionary word. 
 * Return all such possible sentences.
 * 
 * 
 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output: [ "cats and dog", "cat sand dog" ] 
 * 
 * IDEA:
 * 1. defining sub-problem: consider index = 7:
 * 
 * [catsand] + dog - we can get the answer for catsanddog if we know it for catsand:
 * just check the tail = substring(index,len) against dictionary, and if it's true, create the answer from all combinations
 * 
 * catsanddog
 *    |   |
 *  start len = 7  
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
 * O(n^2 + 2^n)
 */
public class Solution140 {

   		static class Words {
   			List<String> list = new ArrayList<>();
   		}

   	
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

            // quick check on the sets of characters - not necessary, just an optimization
            if (!wordCharSet.containsAll(stringCharSet)) {
                return new ArrayList<>();
            }

            int length = s.length();
            // contains a list of all possible cuts for [0,l)
            Words[] dp = new Words[length + 1];// dp[i] the answer for the question if sentence's length is limited to i
            
            for (int i = 0; i < length + 1; ++i) {
                dp[i] = new Words();
            }
            dp[0].list.add("");

            for (int len = 1; len < length + 1; len++) {
                List<String> sublist = new ArrayList<>();

                // fill up the values in the dp array - split the word into to 2 = head + tail
                for (int start = 0; start < len; start++) {
                    String tail = s.substring(start, len);
                    if (wordSet.contains(tail)) {
                        for (String subsentence : dp[start].list) {// this could lead to 2^n overall complexity - aaaa [a, aa, aaa]
                            sublist.add((subsentence + " " + tail).trim());
                        }
                    }
                }
                dp[len].list = sublist;
            }

            return dp[length].list;
        }
  

}
