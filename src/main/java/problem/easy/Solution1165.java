package problem.easy;

/**
 * 
 * There is a special keyboard with all keys in a single row.
 * 
 * Given a string keyboard of length 26 indicating the layout of the keyboard
 * (indexed from 0 to 25). Initially, your finger is at index 0. To type a
 * character, you have to move your finger to the index of the desired
 * character. The time taken to move your finger from index i to index j is |i -
 * j|.
 * 
 * You want to type a string word. Write a function to calculate how much time
 * it takes to type it with one finger.
 */
public class Solution1165 {
	public int calculateTime(String keyboard, String word) {
		char[] keys = word.toCharArray();
		int prev = 0;
		int[] map = new int[26];
		for (int i = 0; i < 26; i++) {
			map[keyboard.charAt(i) - 'a'] = i;
		}
		int cost = 0;
		for (char key : keys) {
			cost += Math.abs(map[key - 'a'] - prev);
			prev = map[key - 'a'];
		}
		return cost;
	}
}
