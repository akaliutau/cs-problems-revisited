package problem.priorityqueue;

import java.util.PriorityQueue;

/**
 * 
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb'
 * or 'ccc' as a substring.
 * 
 * Given three integers a, b and c, return any string s, which satisfies
 * following conditions:
 * 
 * s is happy and longest possible. s contains at most a counter of the
 * letter 'a', at most b counter of the letter 'b' and at most c counter
 * of the letter 'c'. s will only contain 'a', 'b' and 'c' letters. If there is
 * no such string s return the empty string "".
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: a = 1, b = 1, c = 7 Output: "ccaccbcc" Explanation: "ccbccacc" would
 * also be a correct answer.
 * 
 * IDEA:
 * 1. use letters what we have, switching to other letters if needed
 * 
 */
public class Solution1405 {

	class Letter {
		int have;
		char value;
		int counter = 0;

		Letter(int have, char value) {
			this.have = have; // How many [a,b or c] left
			this.value = value; // keep [a,b or c]
		}
		
		public char get() {
			have --;
			counter ++;
			return value;
		}
	}

	public String longestDiverseString(int a, int b, int c) {
		StringBuilder ans = new StringBuilder();

		PriorityQueue<Letter> pq = new PriorityQueue<>((x, y) -> y.have - x.have);
		pq.add(new Letter(a, 'a'));
		pq.add(new Letter(b, 'b'));
		pq.add(new Letter(c, 'c'));

		Letter prev = null;

		while (!pq.isEmpty()) {
			Letter curr = pq.poll();
			if (curr.have == 0) {// drop empty containers
				continue;
			}

			if (prev != null && prev.value == curr.value && prev.counter == 2) {// we are about to add 3 letters in a row
				if (pq.isEmpty())
					break;

				Letter next = pq.poll();
				if (next.have == 0) {
					pq.add(curr);
					continue;
				}
				ans.append(next.get());
				prev = next; // Keep track of last used element

				curr.counter = 0;
				pq.add(next);
			}else {// executed only once
				ans.append(curr.get());
				prev = curr; // Keep track of last used element
			}
			pq.add(curr);
		}
		
		return ans.toString();
	}
}
