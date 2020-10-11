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
 * 
 * 
 * 
 */
public class Solution139 {

	public boolean wordBreak(String s, List<String> wordDict) {
		Queue<Integer> queue = new LinkedList<>();
		int n = s.length();
		boolean visited[] = new boolean[n];
		int pos = 0;
		queue.add(pos);
		visited[0] = true;
		while (!queue.isEmpty()) {
			pos = queue.poll();
			for (String word : wordDict) {
				if (s.startsWith(word, pos)) {
					int next = pos + word.length();
					if (n == next) {
						return true;
					}
					if (!visited[next]) {
						queue.add(next);
						visited[next] = true;
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
