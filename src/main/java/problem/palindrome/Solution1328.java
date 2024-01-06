package problem.palindrome;

/**
 * Given a palindromic string palindrome, replace exactly one character by any
 * lowercase English letter so that the string becomes the lexicographically
 * smallest possible string that isn't a palindrome. After doing so, return the
 * final string. If there is no way to do so, return the empty string. 
 * 
 * Example 1: Input: palindrome = "abccba" Output: "aaccba" 
 * 
 * Example 2: Input: palindrome
 * = "a" Output: ""
 * 
 * IDEA:
 * find any non 'a' letter starting from index 0 and change it to 'a'
 * 
 * after change: 
 * 1) string is not a palindrome anymore, because changes to diff letter 
 * 2) lexicographically smaller, because letter bigger than 'a' has been changed to a
 */
public class Solution1328 {

    public String breakPalindrome(String palindrome) {
        int length = palindrome.length();
        if (length == 0 || length == 1)
            return "";
        char[] ch = palindrome.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            if (ch[i] != 'a') {
                ch[i] = 'a';// after change: 1) not a palindrome, because changes to diff letter 2) smaller, because changed to a
                return String.valueOf(ch);
            }
        }
        // it seemes all letters are a!
        // so change the last one to b
        ch[length - 1] = 'b';
        return String.valueOf(ch);
    }

  

}
