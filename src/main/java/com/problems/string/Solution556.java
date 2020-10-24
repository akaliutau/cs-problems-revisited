package com.problems.string;

/**
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit
 * integer which has exactly the same digits existing in the integer n and is
 * greater in value than n. If no such positive 32-bit integer exists, you need
 * to return -1. 
 * 
 * Example 1: Input: 12 Output: 21
 * 
 * IDEA: 
 * def: inversion 
 * 
 * 12 -> 21
 * 21 -> 12 - wrong
 * 
 * 
 * 1320
 * |
 * 
 * 1320
 *   |
 * 
 * 1320
 * | |
 * 
 * 2310 -> 2013 [3 > 1 > 0] - the biggest
 * 
 * 
 * [d0][BIGGEST]
 * [d1]
 * 
 * d0 - > d1
 */
public class Solution556 {
    
    void reverse(char[] a, int left, int right) {
        while (left < right) {
            swap(a, left, right);
            left++;
            right--;
        }
    }

    void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public int nextGreaterElement(int n) {
        char[] a = ("" + n).toCharArray();
        int len = a.length;
        int i = len - 1;
        
        while (i > 0 && a[i - 1] >= a[i]) {// find the inversion from the end - 1st decreasing number
            i--;
        }
        i = i - 1;
        
        if (i < 0)
            return -1;
        
        int next = i + 1;
        // to form a BIGGER number we need a smaller possible next found from the end
        for (int j = n - 1; j > i; j--) {
            if (a[j] > a[i] && a[j] < a[next]) {
                next = j;
            }
        }

        swap(a, i, next);// increase number
        reverse(a, i + 1, len - 1);// relaxation because swap broke order
        
        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
    }

 
}
