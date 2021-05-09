package problem.hashtable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, sort it in decreasing order based on the frequency of
 * characters.
 * 
 * Example 1:
 * 
 * Input: "tree"
 * 
 * Output: "eert"
 * 
 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e'
 * must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer
 * 
 * 
 * 
 */
public class Solution451 {
	
	static String block(char c, int cnt) {
		char[] let = new char[cnt];
		Arrays.fill(let, c);
		return new String(let);
	}

	public String frequencySort(String s) {

		if (s == null || s.isEmpty())
			return s;

		// Count up the occurrences.
		Map<Character, Integer> counts = new HashMap<>();
		for (char c : s.toCharArray()) {
			counts.compute(c, (k,v) -> v == null ? 1 : v + 1);
		}

		int maximumFrequency = Collections.max(counts.values());

		// Make the list of buckets and apply bucket sort.
		StringBuilder[] buckets = new StringBuilder[maximumFrequency];
		for (char c : counts.keySet()) {
			int idx = counts.get(c);
			if (buckets[idx] == null) {
				buckets[idx] = new StringBuilder();
			}
			buckets[idx].append(block(c,idx));
		}

		// Build up the string.
		StringBuilder sb = new StringBuilder();
		for (int i = maximumFrequency; i >= 0; i--) {
			if (buckets[i] != null) {
				sb.append(buckets[i]);
			}
		}
		return sb.toString();
	}
}
