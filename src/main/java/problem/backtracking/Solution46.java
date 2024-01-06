package problem.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * 
 * IDEA:
 * for each iteration:
 *  1) choose the last number
 *  2) swap it with all the rest numbers, including the last one - as a result the last number iterates through all possible values
 *  3) Repeat until length=1 has reached 
 * 
 *             123          +1 new 
 *             
 *         32 1   13 2      +2 new after swaps 3-1, 3-2 and continue on [0,1]
 *        23 1    31  2     +2 new after swaps 2-3, 3-1 and continue on [0,0] // cycle ends here as len = 1
 *             12 3         +0 new 
 *             21 3         +1 new
 * total = 6 = 3!            
 */
public class Solution46 {

    void swap(int[] a, int i, int j) {
        int c;
        c = a[i];
        a[i] = a[j];
        a[j] = c;
    }

    void perm(int[] array, int n, List<List<Integer>> results) {
        if (n == 1) {
            results.add(Arrays.stream(array).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(array, i, n - 1);
            perm(array, n - 1, results);
            swap(array, i, n - 1);
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        perm(nums, nums.length, results);
        return results;
    }



}
