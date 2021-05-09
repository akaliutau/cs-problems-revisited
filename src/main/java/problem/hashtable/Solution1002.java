package problem.hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array A of strings made only from lowercase letters, return a list
 * of all characters that show up in all strings within the list (including
 * duplicates). For example, if a character occurs 3 times in all strings but
 * not 4 times, you need to include that character three times in the final
 * answer. You may return the answer in any order. 
 * 
 * Example 1: Input:
 * ["bella","label","roller"] Output: ["e","l","l"]
 */
public class Solution1002 {

    public List<String> commonChars(String[] a) {
        List<String> res = new ArrayList<>();
        int words = a.length;
        int[][] common = new int[words][26];
        for (int w = 0; w < words; w++) {
            String word = a[w];
            for (int i = 0; i < word.length(); i++) {
                common[w][word.charAt(i) - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {// count stat for each letter
            int counter = Integer.MAX_VALUE;
            for (int w = 0; w < words; w++) {
                counter = Math.min(counter, common[w][i]);
            }
            if (counter > 0) {
                while (counter-- > 0) {// add duplicates
                    res.add(String.valueOf((char)(i + 'a')));
                }
            }
        }

        return res;

    }

}
