package problem.string;

import java.util.ArrayList;
import java.util.List;

/**
 * String 
 * 
 * Given an integer n, find the closest integer (not including itself),
 * which is a palindrome. The 'closest' is defined as absolute difference
 * minimized between two integers. 
 * 
 * Example 1: Input: "123" Output: "121"
 * 
 * IDEA:
 * 1. Palindrome is symmetric relatively middle point, so cut off the half and generate available options:
 * 
 *     123     -->   half is   12,   12-1,   12+1
 * 
 * Palindrome are:             121   111     131 
 * 
 * there two edge cases in addition:
 * 
 * 1000 - closest palindromes are 1001 and 999
 * 
 * 
 * 
 * 
 * 
 */
public class Solution564 {

    Long getPalindrome(long left, boolean even) {
        long res = left;
        if (!even) {
            left = left / 10;
        }
        while (left > 0) {
            res = res * 10 + left % 10; // add last digit
            left /= 10;
        }
        return res;
    }

    public String nearestPalindromic(String n) {
        int len = n.length();
        boolean isEven = len % 2 == 0;
        int idx = (isEven) ? len / 2 - 1 : len / 2;
        long leftHalf = Long.parseLong(n.substring(0, idx + 1));// half number

        List<Long> candidate = new ArrayList<>(5);
        candidate.add(getPalindrome(leftHalf, isEven));
        candidate.add(getPalindrome(leftHalf + 1, isEven));
        candidate.add(getPalindrome(leftHalf - 1, isEven));
        candidate.add((long) Math.pow(10, len - 1) - 1);
        candidate.add((long) Math.pow(10, len) + 1);

        long diff = Long.MAX_VALUE, res = 0, original = Long.parseLong(n);
        for (Long cand : candidate) {
            if (cand == original)
                continue;
            if (Math.abs(cand - original) < diff) {
                diff = Math.abs(cand - original);
                res = cand;
            } else if (Math.abs(cand - original) == diff) {
                res = Math.min(res, cand);
            }
        }

        return String.valueOf(res);
    }

 

}
