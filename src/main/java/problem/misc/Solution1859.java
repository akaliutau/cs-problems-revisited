package problem.misc;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A sentence is a list of words that are separated by a single space with no
 * leading or trailing spaces. Each word consists of lowercase and uppercase
 * English letters.
 * 
 * A sentence can be shuffled by appending the 1-indexed word position to each
 * word then rearranging the words in the sentence.
 * 
 * For example, the sentence "This is a sentence" can be shuffled as "sentence4
 * a3 is2 This1" or "is2 sentence4 This1 a3". Given a shuffled sentence s
 * containing no more than 9 words, reconstruct and return the original
 * sentence.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "is2 sentence4 This1 a3" Output: "This is a sentence" Explanation:
 * Sort the words in s to their original positions "This1 is2 a3 sentence4",
 * then remove the numbers.
 *
 */
public class Solution1859 {
	
	public String sortSentence(String s) {
        String[] words = s.trim().split("\\s+");
        int n = words.length;
        String[] res = new String[n];
        for (String word : words) {
        	int pos = Integer.valueOf(word.charAt(word.length() - 1) - '1');
        	res[pos] = word.substring(0, word.length() - 1);
        }
        return Arrays.stream(res).collect(Collectors.joining(" "));
    }

}
