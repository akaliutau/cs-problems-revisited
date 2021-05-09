package problem.math;

import java.util.Arrays;

/**
 * You are working in a ball factory where you have n balls numbered from
 * lowLimit up to highLimit inclusive (i.e., n == highLimit - lowLimit + 1), and
 * an infinite number of boxes numbered from 1 to infinity.
 * 
 * Your job at this factory is to put each ball in the box with a number equal
 * to the sum of digits of the ball's number. For example, the ball number 321
 * will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will be
 * put in the box number 1 + 0 = 1.
 * 
 * Given two integers lowLimit and highLimit, return the number of balls in the
 * box with the most balls.
 * 
 * Example 1:
 * 
 * Input: lowLimit = 1, highLimit = 10 Output: 2
 * 
 * Explanation: Box Number: 1 2 3 4 5 6 7 8 9 10 11 ...
 * 
 * Ball Count: 2 1 1 1 1 1 1 1 1 0 0 ...
 * 
 * Box 1 has the most number of balls with 2 balls.
 * 
 * IDEA:
 * max number is 99999 => there are 45 boxes maximum
 * 
 */
public class Solution1742 {

	static int sum(int n) {
		int s = 0;
		while (n > 0) {
			s += n % 10;
			n /= 10;
		}
		return s;
	}

	public int countBalls(int lowLimit, int highLimit) {
		int c[] = new int[46];
		int max = 0;
		for (int i = lowLimit; i <= highLimit; ++i) {
			c[sum(i)]++;
		}
    	max = Arrays.stream(c).max().getAsInt();
		return max;
	}

}
