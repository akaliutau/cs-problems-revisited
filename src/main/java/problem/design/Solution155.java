package problem.design;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * push(x) -- Push element x onto stack. 
 * pop() -- Removes the element on top of the stack. 
 * top() -- Get the top element. 
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * IDEA:
 * 1) update min on push() and return this value on getMin()
 * 2) if the value to return == min, then start  updateMin procedure
 * 
 * O(n) - worst case scenario
 */
public class Solution155 {

	static class MinStack {
		private List<Integer> stack = new ArrayList<>();
		private Integer latestMinElem = Integer.MAX_VALUE;

		public MinStack() {
		}

		public void push(int x) {
			stack.add(x);
			latestMinElem = Math.min(latestMinElem, x);
		}

		public void pop() {
			int elem = top();
			stack.remove(stack.size() - 1);
			if (elem == latestMinElem) {
				updateMin();
			}
		}

		public int top() {
			return stack.get(stack.size() - 1);
		}

		public int getMin() {
			return latestMinElem;
		}

		// look up in the past
		private void updateMin() {
			latestMinElem = Integer.MAX_VALUE;
			for (int i = 0; i < stack.size(); i++) {
				latestMinElem = Math.min(latestMinElem, stack.get(i));
			}
		}
	}



}
