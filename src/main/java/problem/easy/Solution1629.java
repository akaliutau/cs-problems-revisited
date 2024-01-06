package problem.easy;

/**
 * 
 * A newly designed keypad was tested, where a tester pressed a sequence of n
 * keys, one at a time.
 * 
 * You are given a string keysPressed of length n, where keysPressed[i] was the
 * ith key pressed in the testing sequence, and a sorted list releaseTimes,
 * where releaseTimes[i] was the time the ith key was released. Both arrays are
 * 0-indexed. The 0th key was pressed at the time 0, and every subsequent key
 * was pressed at the exact time the previous key was released.
 * 
 * The tester wants to know the key of the keypress that had the longest
 * duration. The ith keypress had a duration of releaseTimes[i] - releaseTimes[i
 * - 1], and the 0th keypress had a duration of releaseTimes[0].
 * 
 * Note that the same key could have been pressed multiple times during the
 * test, and these multiple presses of the same key may not have had the same
 * duration.
 * 
 * Return the key of the keypress that had the longest duration. If there are
 * multiple such keypresses, return the lexicographically largest key of the
 * keypresses.
 * 
 * IDEA:
 * Collect statistics about maximum delays for each letter
 *
 */
public class Solution1629 {

	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int[] maxDelays = new int[26];
		int n = releaseTimes.length;
		char[] letters = keysPressed.toCharArray();
		int firstLetter = letters[0] - 'a'; 
		maxDelays[firstLetter] = releaseTimes[0];
		
		for (int i = 1; i < n; i++) {
			int d = releaseTimes[i] - releaseTimes[i - 1];
			int letter = letters[i] - 'a';
			maxDelays[letter] = Math.max(maxDelays[letter], d);
		}
		
		int max = 0;
		for (int i = 0; i < 26; i++) {
			max = Math.max(max, maxDelays[i]);
		}
		for (int i = 25; i > -1; i--) {
			if (maxDelays[i] == max) {
				return (char) ('a' + i);
			}
		}
		return ' ';
	}

}
