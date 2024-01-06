package problem.dp;

/**
 * Define S = [s,n] as the string S which consists of n connected strings s. For
 * example, ["abc", 3] ="abcabcabc".
 * 
 * On the other hand, we define that string s1 can be obtained from string s2 if
 * we can remove some characters from s2 such that it becomes s1. For example,
 * “abc” can be obtained from “abdbec” based on our definition, but it can not
 * be obtained from “acbbe”.
 * 
 * You are given two non-empty strings s1 and s2 (each at most 100 characters
 * long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the
 * strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer
 * M such that [S2,M] can be obtained from S1.
 * 
 * Example:
 * 
 * Input: s1="acb", n1=4 s2="ab", n2=2
 * 
 * Return: 2
 * 
 */
public class Solution466 {

	public int getMaxRepetitions(String str1, int n1, String str2, int n2) {
		if (n1 == 0)
			return 0;
		int[] indexr = new int[str2.length() + 1]; // index at start of each s1 block
		int[] countr = new int[str2.length() + 1]; // count of repetitions till the present s1 block
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		int index = 0, count = 0;
		for (int i = 0; i < n1; i++) {
			for (int j = 0; j < str1.length(); j++) {
				if (s1[j] == s2[index])
					++index;
				if (index == str2.length()) {
					index = 0;
					++count;
				}
			}
			countr[i] = count;
			indexr[i] = index;
			for (int k = 0; k < i; k++) {
				if (indexr[k] == index) {
					int prevCount = countr[k];
					int patternCount = (countr[i] - countr[k]) * ((n1 - 1 - k) / (i - k));
					int remainCount = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
					return (prevCount + patternCount + remainCount) / n2;
				}
			}
		}
		return countr[n1 - 1] / n2;

	}


}
