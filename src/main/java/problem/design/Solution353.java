package problem.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Design a Snake game that is played on a device with screen size = width x
 * height. Play the game online if you are not familiar with the game.
 * 
 * The snake is initially positioned at the top left corner (0,0) with length =
 * 1 unit.
 * 
 * You are given a list of food's positions in row-column order. When a snake
 * eats the food, its length and the game's score both increase by 1.
 * 
 * Each food appears one by one on the screen. For example, the second food will
 * not appear until the first food was eaten by the snake.
 * 
 * When a food does appear on the screen, it is guaranteed that it will not
 * appear on a block occupied by the snake.
 * 
 * Example:
 * 
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * 
 * Snake snake = new Snake(width, height, food);
 * 
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * 
 * |S| | | 
 * | | |F|
 * 
 * snake.move("R"); -> Returns 0
 * 
 * | |S| | 
 * | | |F|
 * 
 * snake.move("D"); -> Returns 0
 * 
 * | | | | 
 * | |S|F|
 * 
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after
 * that, the second food appears at (0,1) )
 * 
 * | |F| | 
 * | |S|S|
 * 
 * snake.move("U"); -> Returns 1
 * 
 * | |F|S| 
 * | | |S|
 * 
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * 
 * | |S|S| 
 * | | |S|
 * 
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 * 
 * IDEA:
 * use a deque to track all cells which constitute the body of snake
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
		int[] currentPos = null;
		Deque<String> deque = new LinkedList<>();

		public SnakeGame(int width, int height, int[][] food) {
			this.width = width;
			this.height = height;
			this.score = 0;
			this.foodPos = new ArrayList<>(Arrays.asList(food));
			this.deque = new LinkedList<>();

			currentPos = new int[] { 0, 0 };// start position
		}

		/**
		 * Moves the snake.
		 * 
		 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
		 * @return The game's score after the move. Return -1 if game over. Game over
		 *         when snake crosses the screen boundary or bites its body.
		 */

		public int move(String direction) {

			int[] tgtFoodCell = foodPos.size() > 0 ? foodPos.get(0) : new int[] { -1, -1 };

			int[] next = dirMap.get(direction);// convert command to numerical direction

			int x = currentPos[0] + next[0];
			int y = currentPos[1] + next[1];
			currentPos = new int[] { x, y };

			if (x == height || y == width || x < 0 || y < 0 || deque.contains((x + ":" + y))) {
				return -1;
			}

			deque.add(x + ":" + y);

			// the result of execution of this block:
			// either
			// 1) score up, length increased, head moved
			// 2) score the same, length the same, head moved, tail moved
			if (tgtFoodCell[0] == x && tgtFoodCell[1] == y) {
				foodPos.remove(0);
				score++;
			} else {// no inc in length, remove block @ tail
				deque.poll();
			}

			return score;
		}

	}


}
