package problem.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in
 * each row, labelled from 1 to 10 as shown in the figure above.
 * 
 * Given the array reservedSeats containing the numbers of seats already
 * reserved, for example, reservedSeats[i] = [3,8] means the seat located in row
 * 3 and labelled with 8 is already reserved.
 * 
 * Return the maximum number of four-person groups you can assign on the cinema
 * seats. A four-person group occupies four adjacent seats in one single row.
 * Seats across an aisle (such as [3,3] and [3,4]) are not considered to be
 * adjacent, but there is an exceptional case on which an aisle split a
 * four-person group, in that case, the aisle split a four-person group in the
 * middle, which means to have two people on each side.
 * 
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]] Output:
 * 4 Explanation: The figure above shows the optimal allocation for four groups,
 * where seats mark with blue are already reserved and contiguous seats mark
 * with orange are for one group.
 * 
 * IDEA:
 * 
 * [1,2],[1,3],[1,8],[2,6],[3,1],[3,10]
 * 
 * seats     1 2 3 4 5 6 7 8 9 10
 * row         1 1         1
 *                     1
 *           1                 1
 *                
 *  Check all possible variants which are 4 in total 
 * 
 */
public class Solution1386 {
	public static int v2 = Integer.parseInt("001111111100", 2);
	public static int v1a = Integer.parseInt("001111000000", 2);
	public static int v1b = Integer.parseInt("000000111100", 2);
	public static int v1c = Integer.parseInt("000011110000", 2);

	public static int getVariantsInt(int in) {
		if (in == 0) {
			return 2;
		}
		if ((in & v2) == 0) {
			return 2;
		}
		if ((in & v1a) == 0) {
			return 1;
		}
		if ((in & v1b) == 0) {
			return 1;
		}
		if ((in & v1c) == 0) {
			return 1;
		}
		return 0;
	}

	public static int boolToInt(boolean[] r) {
		int num = 0;
		if (r == null) {
			return 0;
		}
		for (int i = 9; i > -1; i--) {
			if (r[i]) {
				num++;
			}
			num = num << 1;
		}
		return num;
	}

	public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
		int nSeats = 0;
		Map<Integer, boolean[]> rows = new HashMap<>();
		for (int[] res : reservedSeats) {
			int row = res[0] - 1;
			if (!rows.containsKey(row)) {
				rows.put(row, new boolean[10]);
			}
			boolean[] r = rows.get(row);
			r[res[1] - 1] = true;
		}
		for (Integer i : rows.keySet()) {
			nSeats += getVariantsInt(boolToInt(rows.get(i)));
		}
		nSeats += 2 * (n - rows.size());
		return nSeats;
	}

}
