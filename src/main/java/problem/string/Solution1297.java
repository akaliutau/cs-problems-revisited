package problem.string;

import java.util.HashMap;

/**
 * Given a string s, return the maximum number of occurrences of any substring
 * under the following rules:
 * 
 * The number of unique characters in the substring must be less than or equal
 * to maxLetters. The substring size must be between minSize and maxSize
 * inclusive.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4 Output: 2
 * 
 * Explanation: Substring "aab" has 2 occurrences in the original string. It
 * satisfies the conditions, 2 unique letters and size 3 (between minSize and
 * maxSize).
 * 
 * IDEA:
 * got n = (maxSize - minSize + 1) times through string, performing on each iteration:
 * 1) track unique letters dynamically
 * 2) collecting statistics about cut part in  freqs map (if and only if the number of unique letters is < maxLetters)
 * 
 * in the end update ans
 *
 */
public class Solution1297 {

	int ans = 0;

	void maxFreq(String s, int maxLetters, int size) {
        HashMap<String, Integer> freqs = new HashMap<>(); 
        int[] map = new int[26]; 
        int unique = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c - 'a'] == 0){
            	unique ++;    
            }
            map[c - 'a'] ++;
            
            if(i < size - 1) continue; // process a head
            
            int first = i - size + 1;
            
            if (unique <= maxLetters) {
                String str = s.substring(first, i + 1);
                freqs.put(str, freqs.getOrDefault(str, 0) + 1);
            }
            //delete first letter of this substring
            c = s.charAt(first);
            map[c - 'a'] --;// always >=0 because this letter has been processed previously 
            if (map[c - 'a'] == 0){
            	unique -- ;
            }
        }

        for(String key: freqs.keySet()) {
        	ans = Math.max(ans, freqs.get(key));
        }
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        for(int size = minSize; size <= maxSize; size++) {
            maxFreq(s, maxLetters, size);
        }
        return ans;
    }

    
}
