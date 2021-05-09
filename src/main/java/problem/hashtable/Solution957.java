package problem.hashtable;

import problem.utils.Utils;
import problem.utils.Utils;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or
 * vacant.
 * 
 * Each day, whether the cell is occupied or vacant changes according to the
 * following rules:
 * 
 * If a cell has two adjacent neighbors that are both occupied or both vacant,
 * then the cell becomes occupied. Otherwise, it becomes vacant. (Note that
 * because the prison is a row, the first and the last cells in the row can't
 * have two adjacent neighbors.)
 * 
 * We describe the current state of the prison in the following way: cells[i] ==
 * 1 if the i-th cell is occupied, else cells[i] == 0.
 * 
 * Given the initial state of the prison, return the state of the prison after N
 * days (and N such changes described above.)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7 Output: [0,0,1,1,0,0,0,0]
 * Explanation: The following table summarizes the state of the prison on each
 * day: 
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1] 
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0] 
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0] 
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0] 
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0] 
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0] 
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * 
 */
public class Solution957 {

	public static int[] prisonAfterNDays(int[] cells, int day) {
		int n = cells.length;
		int[] res = new int[n];
		int prison = cells[0];
		for (int i = 1; i < 8; i++) {
			prison = prison << 1;
			prison += cells[i];
		}
		prison = prison & 255;

		int mask = 126;
		boolean[] states = new boolean[256];
		boolean found = false;
		boolean cycle = false;
		int cycleLen = 0;
		int[] history = new int[256];
		history[cycleLen] = prison;
		while (day > 0) {
			int left = prison >> 1;
			int right = prison << 1;
			int newPrison = ((left ^ right) ^ 255) & mask;
			if (states[newPrison]) {
				cycle = true;
				prison = newPrison;
				break;
			}
			states[newPrison] = true;
			if (newPrison == prison) {
				found = true;
				break;
			}
			prison = newPrison;
			history[++cycleLen] = prison;
			day--;

		}
		if (!found && cycle) {
			int offset = day % cycleLen;
			if (cycleLen != 1) {
				if (offset == 0) {
					offset = cycleLen;
				}
				prison = history[offset];
			}
		}
		for (int i = 7; i > -1; i--) {
			if ((prison & 1) == 1) {
				res[i] = 1;
			}
			prison = prison >> 1;
		}
		return res;
	}

	public static void main(String[] arg) {
		
		int[] cells = {0,1,0,1,1,0,0,1};
		Utils.print(prisonAfterNDays(cells, 7));
	}

	

}
