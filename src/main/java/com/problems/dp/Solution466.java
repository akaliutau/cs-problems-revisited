package com.problems.dp;

/**
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

    public static void main(String[] arg) {

        System.out.println();

    }

}
