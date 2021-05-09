package problem.array;

/**
 * You have a bomb to defuse, and your time is running out! Your informer will
 * provide you with a circular array code of length of n and a key k. To decrypt
 * the code, you must replace every number. All the numbers are replaced
 * simultaneously. 
 * 
 * If k > 0, replace the ith number with the sum of the next k numbers. 
 * 
 * If k < 0, replace the ith number with the sum of the previous k numbers. 
 * 
 * If k == 0, replace the ith number with 0. As code is circular, the
 * next element of code[n-1] is code[0], and the previous element of code[0] is
 * code[n-1]. 
 * 
 * Given the circular array code and an integer key k, return the
 * decrypted code to defuse the bomb! 
 * 
 * Example 1: Input: code = [5,7,1,4], k = 3 Output: [12,10,16,13] 
 * 
 * Explanation: Each number is replaced by the sum of the
 * next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice
 * that the numbers wrap around.
 */
public class Solution1652 {
    
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] decoded = new int[n];
        if (k == 0) {
            return decoded;
        }
        int[] sum = new int[n];// sum_i = sum(i,i+k-1) exclusive
        int kSum = 0;
        // calc the very first sum
        int len = Math.abs(k);
        for (int i = 0; i < len; i++) {
            kSum += code[i % n];
        }
        sum[0] = kSum;
        for (int i = 1; i < n; i++) {
            int less = code[i - 1];
            int more = code[(i + len - 1) % n];
            kSum += (more - less);
            sum[i] = kSum;
        }
        if (k > 0) {
            for (int i = 0; i < n; i++) {
                decoded[i] = sum[(i + 1) % n]; 
            }            
        }else {
            for (int i = 0; i < n; i++) {
                decoded[(len + i) % n] = sum[i]; 
            }            
        }
        
        return decoded;
    }

}
