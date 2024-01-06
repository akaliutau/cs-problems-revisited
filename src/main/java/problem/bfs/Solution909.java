package problem.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * BFS 
 * 
 * On an N x N board, the numbers from 1 to N*N are written
 * boustrophedonically starting from the bottom left of the board, and
 * alternating direction each row. For example, for a 6 x 6 board, the numbers
 * are written as follows:
 * 
 * 16 15 14 13
 * 9  10 11 12
 * 8   7  6  5
 * 1   2  3  4
 * 
 * 
 */
public class Solution909 {

	// Given a square num s, return board coordinates (r, c) as r*n + c
	int get(int s, int n) {
		int rrow = (s - 1) / n;
		int rem = (s - 1) % n;
		int row = n - 1 - rrow;
		int col = row % 2 != n % 2 ? rem : n - 1 - rem;
		return row * n + col;
	}

	public int snakesAndLadders(int[][] board) {
		int n = board.length;

		Map<Integer, Integer> dist = new HashMap<>();
		dist.put(1, 0);

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		while (!queue.isEmpty()) {
			int s = queue.remove();
			if (s == n * n)
				return dist.get(s);

			for (int s2 = s + 1; s2 <= Math.min(s + 6, n * n); ++s2) {
				int rc = get(s2, n);
				int r = rc / n, c = rc % n;
				int s2Final = board[r][c] == -1 ? s2 : board[r][c];
				if (!dist.containsKey(s2Final)) {// already been reached
					dist.put(s2Final, dist.get(s) + 1);
					queue.add(s2Final);
				}
			}
		}
		return -1;
	}

}
