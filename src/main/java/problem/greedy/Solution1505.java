package problem.greedy;

/**
 * Given a string num representing the digits of a very large integer and an
 * integer k. You are allowed to swap any two adjacent digits of the integer at
 * most k times. Return the minimum integer you can obtain also as a string.
 * 
 * Input: num = "4321", k = 4 Output: "1342" 
 * 
 * Explanation: The steps to obtain
 * the minimum integer from 4321 with 4 adjacent swaps are shown. 
 * 4321 -> 3421 -> 3412 -> 3142 -> 1342
 * 
 * IDEA: 
 * decrease layer by layer, bubble-up the smallest and the most distant digit
 * 4321 -> 1432
 * 1432 -> 1342
 * 
 */
public class Solution1505 {

    public String minInteger(String num, int k) {
        char[] arr = num.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            // find the most distant AND smallest digit (i+k)th at most, smaller than ith
            // always try to decrease first digits first
            int pos = i;
            for (int j = i + 1; j < n && j <= i + k; ++j) {
                if (arr[pos] > arr[j]) {
                    pos = j;
                }
            }
            // circle shift [i] <=> [pos]
            char temp = arr[pos];
            while (pos != i){
                arr[pos] = arr[pos - 1];
                --k;
                --pos;
            }
            arr[i] = temp;
            if (k <=0 ) {
                break;
            }
        }
        return new String(arr);
    }



}
