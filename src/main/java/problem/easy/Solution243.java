package problem.easy;

/**
 * 
 * Given an array of strings wordsDict and two different strings that already
 * exist in the array word1 and word2, return the shortest distance between
 * these two words in the list.
 * 
 * IDEA:
 * 
 * track only the latest positions of both words, and update distance on each new word
 * 
 * why this is working:
 * 
 * case1:
 * 1a   1b  2a  <--   will be calculated distances 1a-1b and 1b-2a, others will be farer
 * 
 * case2:
 * 1a 2a 1b     <--   will be calculated distances 2a-1b only,  as 1a will be overwritten
 * 
 * 
 */
public class Solution243 {
	public int shortestDistance(String[] wordsDict, String word1, String word2) {
		int pos1 = -1;
		int pos2 = -1;
		int dist = Integer.MAX_VALUE;

		int idx = 0;
		for (String word : wordsDict) {
			if (word.equals(word1)) {
				pos1 = idx;
			}
			if (word.equals(word2)) {
				pos2 = idx;
			}
			if (pos1 != -1 && pos2 != -1) {
				dist = Math.min(dist, Math.abs(pos1 - pos2));
			}
			idx++;
		}

		return dist;
	}
}
