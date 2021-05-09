package problem.string;

/**
 * A string s is nice if, for every letter of the alphabet that s contains, it
 * appears both in uppercase and lowercase. For example, "abABB" is nice because
 * 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b'
 * appears, but 'B' does not.
 * 
 * Given a string s, return the longest substring of s that is nice. If there
 * are multiple, return the substring of the earliest occurrence. If there are
 * none, return an empty string.
 * 
 * Example 1:
 * 
 * Input: s = "YazaAay" Output: "aAa" 
 * 
 * Explanation: "aAa" is a nice string
 * because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a'
 * appear. "aAa" is the longest nice substring.
 * 
 * IDEA:
 * 
 * maintain the running vectors of all letters for each substrings [l,r] and choose the longest one 
 *
 */
public class Solution1763 {
	
	static boolean isNice(int[] lower, int[] higher, int[] lowerL, int[] higherL) {
		for (int i = 0; i < 26; i++) {
			if ((lower[i] - lowerL[i] == 0 && higher[i] - higherL[i] > 0) || (lower[i] - lowerL[i] > 0 && higher[i] - higherL[i] == 0)) {
				return false;
			}
		}
		return true;
	}
	
	public String longestNiceSubstring(String s) {
		char[] letters = s.toCharArray();
		int n = letters.length;
		int left = 0;
		int right = 0;
		int len = 0;
		int[] lowerRunning = new int[26];
		int[] higherRunning = new int[26];
		for (int l = 0; l < n; l++) {
			int[] lower = lowerRunning.clone();
			int[] higher = higherRunning.clone();
			for (int r = l; r < n; r++) {
				char c = letters[r];
				if (c >= 'a' && c <= 'z') {
					lower[c - 'a'] ++;
				}else{
					higher[c - 'A'] ++;
				}
				if (isNice(lower, higher, lowerRunning, higherRunning)) {
					if (r - l + 1 > len) {
						right = r;
						left = l;
                        len = r - l + 1;
					}
				}
			}			
			char b = letters[l];
			if (b >= 'a' && b <= 'z') {
				lowerRunning[b - 'a'] ++;
			}else {
				higherRunning[b - 'A'] ++;
			}
		}
		if (left == right){
            return "";
        }
		return s.substring(left, right + 1);
	}
	
}
