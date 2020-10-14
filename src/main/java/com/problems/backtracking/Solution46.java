package com.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Backtracking
 * 
 *             123          +1 new
 *         32 1   13 2      +2 new
 *        23 1    31  2     +2 new
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



	public static void main(String[] arg) {
		
		System.out.println();

	}

}
