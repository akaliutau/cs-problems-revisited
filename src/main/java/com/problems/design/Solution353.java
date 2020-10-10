package com.problems.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * Design game
 * 
 */
public class Solution353 {

	static class SnakeGame {

		static Map<String, int[]> dirMap = new HashMap<>();

		static {
			dirMap.put("U", new int[] { -1, 0 });
			dirMap.put("L", new int[] { 0, -1 });
			dirMap.put("R", new int[] { 0, 1 });
			dirMap.put("D", new int[] { 1, 0 });
		}

		int width;
		int height;
		int score;

		List<int[]> foodPos;
		Queue<int[]> queue = new LinkedList<>();
		Deque<String> deque = new LinkedList<>();

		public SnakeGame(int width, int height, int[][] food) {
			this.width = width;
			this.height = height;
			this.score = 0;
			this.foodPos = new ArrayList<>(Arrays.asList(food));
			this.deque = new LinkedList<>();

			queue.add(new int[] { 0, 0 });
		}

		/**
		 * Moves the snake.
		 * 
		 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
		 * @return The game's score after the move. Return -1 if game over. Game over
		 *         when snake crosses the screen boundary or bites its body.
		 */

		public int move(String direction) {

			int[] currentPos = queue.remove();
			int[] currentFood = foodPos.size() > 0 ? foodPos.get(0) : new int[] { -1, -1 };

			int[] next = dirMap.get(direction);

			int x = currentPos[0] + next[0];
			int y = currentPos[1] + next[1];
			queue.add(new int[] { x, y });

			if (x == height || y == width || x < 0 || y < 0 || deque.contains((x + ":" + y))) {
				return -1;
			}

			deque.add(x + ":" + y);

			if (currentFood[0] == x && currentFood[1] == y) {
				foodPos.remove(0);
				score++;
			} else if (deque.size() > score)
				deque.removeFirst();

			return score;
		}

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
