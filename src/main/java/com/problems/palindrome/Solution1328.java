package com.problems.palindrome;

/**
 *
 */
public class Solution1328 {

    public String breakPalindrome(String palindrome) {
        int length = palindrome.length();
        if (length == 0 || length == 1)
            return "";
        char[] ch = palindrome.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            if (ch[i] != 'a') {
                ch[i] = 'a';
                return String.valueOf(ch);
            }
        }
        ch[length - 1] = 'b';
        return String.valueOf(ch);
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
