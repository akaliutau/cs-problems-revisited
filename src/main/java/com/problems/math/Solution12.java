package com.problems.math;

/**
 * String
 */
public class Solution12 {

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
        int[] integers = new int[]     { 1,    4,    5,    9,  10,  40,   50,  90,  100,  400,  500,  900, 1000 };
        for (int i = integers.length - 1; i >= 0; i--) {
            int times = num / integers[i];// how many times the latin sym must be repeated
            num %= integers[i];// assign the tail to num
            while (times-- > 0) {
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
