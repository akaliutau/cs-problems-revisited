package problem.advancedtopics;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/time-needed-to-rearrange-a-binary-string/
 * 
 * You are given a binary string s. In one second, all occurrences of "01" are
 * simultaneously replaced with "10". This process repeats until no occurrences
 * of "01" exist.
 * 
 * Return the number of seconds needed to complete this process.
 * 
 */

public class Solution357 {
	
	static boolean sorted(char[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i++) {
			if (arr[i-1] == '0' && arr[i] == '1') {
				return false;
			}
		}
		return true;
	}

	
    public static int secondsToRemoveOccurrences(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n == 0 || n == 1){
            return 0;
        }
        int ans = 0;
    	System.out.println(Arrays.toString(arr));
        while (!sorted(arr)) {
        	char[] chars = arr.clone();
    		for (int i = 1; i < n; i++) {
    			if (arr[i-1] == '0' && arr[i] == '1') {
    				chars[i] = '0';
    				chars[i-1] = '1';
    			}
    		}
        	System.out.println(Arrays.toString(chars));
        	arr = chars;
        	ans ++;
        }

        return ans;
    }
    
	public static void main(String[] arg) {
		System.out.println(secondsToRemoveOccurrences("1001111111110001011001110000000110101"));
	}


}
