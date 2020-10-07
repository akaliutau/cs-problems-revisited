package com.problems.stack;

import java.util.Stack;

/**
 * Stack
 * 
 */
public class Solution682 {

	public int calPoints(String[] ops) {

		Stack<Integer> stack = new Stack<>();
		int points = 0;
		for (int i = 0; i < ops.length; i++) {
			int n = stack.size();
			if (ops[i].equals("+")) {
				int change = stack.get(n - 1) + stack.get(n - 2);
				points += change;
				stack.push(change);
			} else if (ops[i].equals("D")) {
				int change = 2 * stack.lastElement();
				points += change;
				stack.push(change);
			} else if (ops[i].equals("C")) {
				if (!stack.isEmpty()) {
					int lastChange = stack.pop();
					points -= lastChange;
				}
			} else {
				int change = Integer.valueOf(ops[i]);
				points += change;
				stack.push(change);
			}
		}
		return points;

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
