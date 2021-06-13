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
 * 
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
 * O(n)
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

            int len = s.length();
            // contains a list of all possible cuts for [0,l)
            Words[] dp = new Words[len + 1];// dp[i] the answer for the question if sentence's length is limited to i
            
            for (int i = 0; i < len + 1; ++i) {
                dp[i] = new Words();
            }
            dp[0].list.add("");

            for (int end = 1; end < len + 1; end++) {
                List<String> sublist = new ArrayList<>();

                // fill up the values in the dp array - split the word into to 2 = head + tail
                for (int start = 0; start < end; start++) {
                    String tail = s.substring(start, end);
                    if (wordSet.contains(tail)) {
                        for (String subsentence : dp[start].list) {
                            sublist.add((subsentence + " " + tail).trim());
                        }
                    }
                }
                dp[end].list = sublist;
            }

            return dp[len].list;
        }
  

}
