package com.problems.dp;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * DP
 * 
 * catsanddog
 * 
 * dict = [cat, cats, and, dog ]
 *             catsanddog
 *            /         \
 * 		   sanddog     anddog
 *        		
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

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
