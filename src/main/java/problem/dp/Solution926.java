package problem.dp;

/**
 * 
 * A binary string is monotone increasing if it consists of some number of 0's
 * (possibly none), followed by some number of 1's (also possibly none).
 * 
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or
 * from 1 to 0.
 * 
 * Return the minimum number of flips to make s monotone increasing.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "00110" Output: 1 
 * Explanation: We flip the last digit to get 00111. *
 * 
 * IDEA:
 * 
 * for each position in [1,n-2]:
 *   calculate the number of ones to the left and the number of zeros to the right
 *   
 * evaluate also the edges
 * 
 * 
 */
public class Solution926 {

	public int minFlipsMonoIncr(String s) {
        char[] digits = s.toCharArray();
        int n = digits.length;
        int[] onesl = new int[n];
        int[] zerosr = new int[n];
        for (int i = 0; i < n; i++){
            if (digits[i] == '0'){
                onesl[i] = i == 0 ? 0 : onesl[i - 1];
            }else{
                onesl[i] = i == 0 ? 1 : onesl[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; i--){
            if (digits[i] == '0'){
                zerosr[i] = i == n - 1 ? 1 : zerosr[i + 1] + 1;
            }else{
                zerosr[i] = i == n - 1 ? 0 : zerosr[i + 1];
            }
        }
        int min = n;
        for (int i = 1; i < n - 1; i++){
            min = Math.min(min, zerosr[i + 1] + onesl[i - 1]);
        }        
        min = Math.min(min, onesl[n-1]);
        min = Math.min(min, zerosr[0]);
        return min;
    }
}
