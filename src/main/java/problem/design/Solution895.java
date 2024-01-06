package problem.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Implement FreqStack, a class which simulates the operation of a stack-like
 * data structure.
 * 
 * FreqStack has two functions:
 * 
 * push(int x), which pushes an integer x onto the stack. 
 * 
 * pop(), which removes and returns the most frequent element in the stack. 
 * 
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed
 * and returned.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]] Output:
 * [null,null,null,null,null,null,null,5,7,5,4]
 * 
 * Explanation: After making six .push operations, the stack is [5,7,5,7,4,5]
 * from bottom to top.
 * 
 * Then:
 * 
 * pop() -> returns 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
 * 
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
 * 
 * pop() -> returns 5. The stack becomes [5,7,4].
 * 
 * pop() -> returns 4. The stack becomes [5,7].
 * 
 * IDEA:
 * build step structure which holds frequency for each element
 * 
 *           3
 *       1   0
 *       0   1   1   7
 * ____________________  
 * group 1   2   3   4
 */
public class Solution895 {

	class FreqStack {
		Map<Integer, Integer> freq;
		Map<Integer, Stack<Integer>> group;// if there are multiple elems with freq = f
		int maxfreq;

		public FreqStack() {
			freq = new HashMap<>();
			group = new HashMap<>();
			maxfreq = 0;
		}

		public void push(int x) {
			int newFreq = freq.getOrDefault(x, 0) + 1;
			freq.put(x, newFreq);// update frequency for element x 
			maxfreq = Math.max(maxfreq, newFreq);
			group.computeIfAbsent(newFreq, z -> new Stack<>()).push(x);
		}

		public int pop() {
			int x = group.get(maxfreq).pop();
			freq.compute(x, (k,v) -> v - 1);// update frequency for element x 
			if (group.get(maxfreq).size() == 0) {// there are always elems of all frequencies from [0, maxfreq]
				maxfreq--;
			}
			return x;
		}
	}



}
