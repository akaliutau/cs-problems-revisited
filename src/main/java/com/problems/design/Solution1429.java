package com.problems.design;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * 
 */
public class Solution1429 {


	class FirstUnique {

		Queue<Integer> queue = new ArrayDeque<>();
		Map<Integer, Boolean> unique = new HashMap<>();

		public FirstUnique(int[] nums) {
			for (int num : nums) {
				this.add(num);
			}
		}

		public void add(int value) {
			if (!unique.containsKey(value)) {
				unique.put(value, true);
				queue.add(value);
			} else {
				unique.put(value, false);
			}
		}

		public int showFirstUnique() {
			// go through all queue and remove all non-unique from queue (map still hold the info about uniqueness value)
			while (!queue.isEmpty() && !unique.get(queue.peek())) {
				queue.remove();
			}

			if (!queue.isEmpty()) {
				return queue.peek();
			}
			return -1;
		}

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
