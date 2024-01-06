package problem.dp;

/**
 * 
 * Given a string s, return the number of substrings that have only one distinct
 * letter.
 * 
 * Example 1:
 * 
 * Input: s = "aaaba" Output: 8
 * 
 * IDEA:
 * 1. in a monolith block of letters:
 *   a    - 1 variant
 *   aa   - combine current with all previous = 1 + 1
 *   aaa  - combine current with all previous = 1 + 1 + 1
 * 
 *   total = sum of all combinations (line 31)
 *   
 *  2. counter is reseted on each change
 */
public class Solution1180 {

	public int countLetters(String s) {
        int total = 1, count = 1;
        char[] letters = s.toCharArray();
        for (int i = 1; i < letters.length; i++) {
            if (letters[i] == letters[i-1]) {
                count++;
            } else {
                count = 1;
            }
            total += count;
        }
        return total;
    }
}
