package problem.math;

import java.util.Stack;

/**
 * Given two numbers arr1 and arr2 in base -2, return the result of adding them
 * together. Each number is given in array format: as an array of 0s and 1s,
 * from most significant bit to least significant bit. For example, arr =
 * [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3. A number arr
 * in array, format is also guaranteed to have no leading zeros: either arr ==
 * [0] or arr[0] == 1. Return the result of adding arr1 and arr2 in the same
 * format: as an array of 0s and 1s with no leading zeros. 
 * 
 * Example 1: Input:
 * arr1 = [1,1,1,1,1], arr2 = [1,0,1] Output: [1,0,0,0,0] Explanation: arr1
 * represents 11, arr2 represents 5, the output represents 16. 
 * 
 * IDEA: carry populates to two digits to the left 
 *       [1]
 *     + [1] 
 *  -------- 
 *   [1,1,0]
 */
public class Solution1073 {
    
    static int[] reverse(int[] arr) {
        int n = arr.length;
        int[] ret = new int[n];
        for (int i = n - 1; i > -1; i--) {
            ret[i] = arr[n - 1 - i];
        }
        return ret;
    }
    
    public int[] addNegabinary(int[] a1, int[] a2) {
        
        int l1 = a1.length;
        int l2 = a2.length;
        int i = 0;
        int carry = 0;
        int n = Math.max(l1, l2);
        Stack<Integer> res = new Stack<>();
        int[] arr1 = reverse(a1);
        int[] arr2 = reverse(a2);
        while (i < n) {
            int num1 = i >= l1 ? 0 : arr1[i];
            int num2 = i >= l2 ? 0 : arr2[i];
            int sum = num1 + num2 + carry;
            int remains = 0;
    //  if sum == -1,  negabinary representation  11 => 1(res), 1(carry) in binary
            if (sum < 0) {
                remains = 1;
                carry = 1;
            }else {
                remains = sum % 2;
                carry = (-1)*(sum/2);
            }
    //  if sum == 0 negabinary representation 00, 0(res), 0(carry)
    //  if sum == 1 negabinary representation 10, 1(res), 0(carry)
    //  if sum == 2 negabinary representation 0`11`, 0(res), -1(carry)
    //  if sum == 3 negabinary representation 1`11`, 1(res), -1(carry)
            
            res.add(remains);
            i ++;
        }
    //# If carry pending, apply same representation
        if (carry == -1) {
            res.add(1);
            res.add(1);
        }else if (carry == 1) {
            res.add(1);
        }
    //# remove tail from 0's
        i = res.size() - 1;
        while (i > 0 && res.peek() == 0) {
            res.pop();
            i--;
        }
        int [] ans = new int[res.size()];
        int idx = 0;
        for (int j = res.size() - 1; j > -1; j--) {
            ans[idx++] = res.get(j);
        }
        return ans;
    }

}
