package problem.design;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * You have a queue of integers, you need to retrieve the first unique integer
 * in the queue.
 * 
 * Implement the FirstUnique class:
 * 
 * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 * int showFirstUnique() returns the value of the first unique integer of the
 * queue, and returns -1 if there is no such integer. void add(int value) insert
 * value to the queue.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]] Output: [null,2,null,2,null,3,null,-1]
 * Explanation: 
 * 
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2 
 * firstUnique.add(5); // the queue is now [2,3,5,5] 
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2); // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3 
 * firstUnique.add(3); // the queue is now [2,3,5,5,2,3] 
 * firstUnique.showFirstUnique(); // return -1
 * 
 * IDEA:
 * 1) use queue as a buffer, which is processed each time when showFirstUnique() is invoked
 * 2) use a map structure to hold info about uniqueness
 * 
 */
public class Solution1429 {

	class FirstUnique {

		Queue<Integer> queue = new ArrayDeque<>();// used as a buffer, which is processed on showFirstUnique invoke
		Map<Integer, Boolean> unique = new HashMap<>(); // grows indefinitely

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
			// go through all queue and remove all non-unique from queue
			// map still hold the info about uniqueness value though
			
			while (!queue.isEmpty() && !unique.get(queue.peek())) {
				queue.remove();
			}

			// as a result queue will contain only unique values now
			if (!queue.isEmpty()) {
				return queue.peek();
			}
			return -1;
		}

	}


}
