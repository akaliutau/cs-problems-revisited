package com.problems.stack;

import java.util.Stack;

/**
 * 
 * Stack
 * 
 * asteroids = [5, 10, -5] 
 * Output: [5, 10]
 * 
 * asteroids = [-5, 10, -5] 
 * Output: [-5, 10]
 */
public class Solution735 {

	public int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();// accumulates only survived so far asteroids
		for (int asteroid : asteroids) {
			boolean collision = false;
			// check each negative asteroid for collision
			if (asteroid < 0) {
				while (!stack.isEmpty() && stack.peek() > 0) {// check only positive
					if (stack.peek() < Math.abs(asteroid)) {// compare sizes
						stack.pop();
						continue;// asteroid wins and survives
					} else if (stack.peek() == Math.abs(asteroid)) {
						stack.pop();
					}
					collision = true;
					break;
				}
			}
			if (!collision) {
				stack.push(asteroid);
			}
		}

		int[] ans = new int[stack.size()];
		for (int t = ans.length - 1; t >= 0; --t) {
			ans[t] = stack.pop();
		}
		return ans;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
