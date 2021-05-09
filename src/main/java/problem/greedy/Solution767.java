package problem.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result. If not possible, return the empty
 * string.
 * 
 * Example 1:
 * 
 * Input: S = "aab" Output: "aba"
 * 
 * IDEA:
 * use the most frequent letters first
 * 
 */
public class Solution767 {

	class Letter {
		int count;
		char letter;

		Letter(int ct, char ch) {
			count = ct;
			letter = ch;
		}
	}

	public String reorganizeString(String s) {
		int n = s.length();
		int[] count = new int[26];// statistics
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		
		Comparator<Letter> byFreq = (a, b) -> b.count - a.count;
		Comparator<Letter> byLetter = (a, b) -> a.letter - b.letter;
		PriorityQueue<Letter> pq = new PriorityQueue<>(byFreq.thenComparing(byLetter));

		for (int i = 0; i < 26; ++i) {
			if (count[i] > 0) {
				if (count[i] > (n + 1) / 2) {// too many equal symbols
					return "";
				}
				pq.add(new Letter(count[i], (char) ('a' + i)));// add to collector
			}
		}

		StringBuilder ans = new StringBuilder();
		while (pq.size() >= 2) {
			Letter let1 = pq.poll();
			Letter let2 = pq.poll();

			ans.append(let1.letter);
			ans.append(let2.letter);
			if (--let1.count > 0)
				pq.add(let1);
			if (--let2.count > 0)
				pq.add(let2);
		}

		if (pq.size() > 0) {// odd length - one letter possibly has been left on queue
			ans.append(pq.poll().letter);
		}
		return ans.toString();
	}


}
