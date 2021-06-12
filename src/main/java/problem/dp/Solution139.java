package problem.dp;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * catsanddog
 * 
 * dict = [cat, cats, and, dog ]
 *             catsanddog
 *            /         \
 * 		   sanddog     anddog   <-- substring queued for analysis after 1st iteration
 *        		
 * IDEA:
 * 1. iterate through all cuts (total n)
 * 2. use BFS to add incrementally possible cuts  
 * 
 */
public class Solution139 {

	public boolean wordBreak(String s, List<String> wordDict) {
		Queue<Integer> positions = new LinkedList<>();//
		int n = s.length();
		boolean visitedPositions[] = new boolean[n];
		int pos = 0;
		positions.add(pos);
		visitedPositions[0] = true;
		while (!positions.isEmpty()) {
			pos = positions.poll();
			for (String word : wordDict) {
				if (s.startsWith(word, pos)) {
					int nextPos = pos + word.length();
					if (n == nextPos) {
						return true;
					}
					if (!visitedPositions[nextPos]) {
						positions.add(nextPos);
						visitedPositions[nextPos] = true;
					}
				}
			}
		}
		return pos == n;

	}

	

}
