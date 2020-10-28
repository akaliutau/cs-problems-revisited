package com.problems.segmenttree;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive. The update(i, val) function modifies nums by
 * updating the element at index i to val. 
 * 
 * Example: Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8 
 * 
 * Segment tree is a very
 * flexible data structure, because it is used to solve numerous range query
 * problems like finding minimum, maximum, sum, greatest common divisor, least
 * common denominator in array in logarithmic time. 
 * 
 * The segment tree for array
 * a[0, 1, ... ,n-1] is a binary tree in which each node contains aggregate
 * information (min, max, sum, etc.) for a subrange [i ... j] of the array, as
 * its left and right child hold information for range [i ... {i+j}/2] and
 * [{i+j}/2 + 1, j] 
 * 
 * Segment tree could be implemented using either an array or a
 * tree. For an array implementation, if the element at index ii is not a leaf,
 * its left and right child are stored at index 2i and 2i + 1 respectively.
 */
public class Solution307 {

    class NumArray {

        int[] tree;
        int n;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }

        private void buildTree(int[] nums) {
            for (int i = n, j = 0; i < 2 * n; i++, j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }

        void update(int pos, int val) {
            pos += n;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos;
                int right = pos;
                if (pos % 2 == 0) {
                    right = pos + 1;
                } else {
                    left = pos - 1;
                }
                // parent is updated after child is updated
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int l, int r) {
            // get leaf with value 'l'
            l += n;
            // get leaf with value 'r'
            r += n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }

}
