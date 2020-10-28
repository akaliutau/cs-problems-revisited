package com.problems.string;

/**
 * Given an array of characters chars, compress it using the following
 * algorithm: Begin with an empty string s. For each group of consecutive
 * repeating characters in chars: If the group's length is 1, append the
 * character to s. Otherwise, append the character followed by the group's
 * length. The compressed string s should not be returned separately, but
 * instead be stored in the input character array chars. Note that group lengths
 * that are 10 or longer will be split into multiple characters in chars. After
 * you are done modifying the input array, return the new length of the array
 */
public class Solution443 {

    public int compress(char[] chars) {
        int last = 0, write = 0;
        int n = chars.length;
        for (int cur = 0; cur < n; cur++) {
            if (cur + 1 == n || chars[cur + 1] != chars[cur]) {// trigger this block on last symbol or symbol change
                chars[write++] = chars[last];// write letter
                if (cur > last) {// if block > 1 write also the counter
                    for (char c : ("" + (cur - last + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                last = cur + 1;
            }
        }
        return write;
    }

}
