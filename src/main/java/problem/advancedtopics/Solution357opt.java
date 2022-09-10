package problem.advancedtopics;

/**
 * https://leetcode.com/problems/time-needed-to-rearrange-a-binary-string/
 * 
 * You are given a binary string s. In one second, all occurrences of "01" are
 * simultaneously replaced with "10". This process repeats until no occurrences
 * of "01" exist.
 * 
 * Return the number of seconds needed to complete this process.
 * 
 */
public class Solution357opt {

	public static int secondsToRemoveOccurrences(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		if (n == 0 || n == 1) {
			return 0;
		}
		int last = n - 1;
		while (last >= 0 && arr[last] == '0') {
			last--;
		}
		if (last < 0)
			return 0;
		int ans = 0, pos = 0;
		for (int i = 0; i < last; i++) {
			if (arr[i] == '0')
				ans++;
		}
		if (ans == 0)
			return 0;

		while (pos < last && arr[pos] == '1') {
			pos++;
		}
		int excess = 0;
		while (pos < last) {
			if (arr[pos] == '1') {
				excess++;
			} else {
				excess--;
			}
			excess = Math.max(excess, 0);
			pos++;
		}

		return ans + excess;

	}

	public static void main(String[] arg) {
		System.out.println(secondsToRemoveOccurrences("1001111111110001011001110000000110101"));
	}

}
