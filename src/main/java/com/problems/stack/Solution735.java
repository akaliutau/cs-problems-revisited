package com.problems.stack;

import java.util.Stack;

/**
 * 
 * We are given an array asteroids of integers representing asteroids in a row.
 * 
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * 
 * Find out the state of the asteroids after all collisions. If two asteroids
 * meet, the smaller one will explode. If both are the same size, both will
 * explode. Two asteroids moving in the same direction will never meet.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: asteroids = [5,10,-5] Output: [5,10] Explanation: The 10 and -5
 * collide resulting in 10. The 5 and 10 never collide.
 * 
 * asteroids = [5, 10, -5] Output: [5, 10]
 * 
 * asteroids = [-5, 10, -5] Output: [-5, 10]
 * 
 * IDEA:
 *  1) consider only one direction
 *  2) for each negative asteroid:
 *       check it's survival 
 *       
 *  the final state of Stack will show all survived asteroids 
 */
public class Solution735 {

	public int[] asteroidCollision(int[] asteroids) {
		Stack<Integer> stack = new Stack<>();// accumulates only survived so far asteroids
		for (int asteroid : asteroids) {
			boolean bothdestroyed = false;
			// check each negative asteroid for collision
			if (asteroid < 0) {
				while (!stack.isEmpty() && stack.peek() > 0) {// check only positive
					if (stack.peek() < Math.abs(asteroid)) {// compare sizes
						stack.pop();
						continue;// asteroid wins and survives
					} else if (stack.peek() == Math.abs(asteroid)) {
						stack.pop();
						bothdestroyed = true;
						break;
					}
				}
			}
			if (!bothdestroyed) {
				stack.push(asteroid);
			}
		}

		int[] ans = new int[stack.size()];
		for (int t = ans.length - 1; t >= 0; --t) {
			ans[t] = stack.pop();
		}
		return ans;
	}



}
