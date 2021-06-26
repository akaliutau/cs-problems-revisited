package problem.segmenttree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [5,2,6,1] Output: [2,1,1,0]
 * 
 * Explanation: To the right of 5 there are 2 smaller elements (2 and 1). To the
 * right of 2 there is only 1 smaller element (1). To the right of 6 there is 1
 * smaller element (1). To the right of 1 there is 0 smaller element.
 * 
 * IDEA:
 * Build Segment Tree to solve the problem.
 * 
 *                       [0 5 2 6 1]
 *                 
 *   [0,6: 0]  - initial array in format [range: counter], here is an example when no segments added
 *   
 *   after adding num=1, the tree will look like:
 *   
 *                    		   [0,6: 1]                 
 *                          /           \
 *                      [0,3:1]          [4,6:0] 
 *                      /     \
 *                  [0,1:1]   [2,3:0]    
 * 
 * Initial state: []
 * query(1) = 0 <-- Time = O(log n)
 * add(1)
 * 
 * query(6) = 1 <-- Time = O(log n)
 * add(6)
 *                     
 * Solution: Build tree dynamically calculating intermediate results 
 * 
 * Acceleration:
 * 
 * For a particular element in nums, located at index i, we want to count how many of the numbers on the right side of index i are smaller than nums[i].
 * Since we need counts of values, we can use an approach similar to bucket sort, where we have buckets of values and buckets[value] stores the count of value
 * 
 * nums:         [5,2,6,1]
 * buckets:  [0 1 1 0 0 0 1]           ]
 *            0 1 2 3 4 5 6     <-- needed max(nums) buckets to track all numbers
 *            
 * number of elems < 5 == number of 1s in the range (-inf,5)
 * 
 * 1. Dynamically build Segment Tree during traversing 1 -> 6 -> 2 -> 5
 * 2. Query tree to find the # of 1s in the range
 * 
 */
public class Solution315 {
	
	static class Segment {
		int[] range;
		int count = 0;
		Segment left;
		Segment right;
		
		public Segment(int min, int max) {
			this.range = new int[] {min, max};
		}
		
		public boolean isLeaf() {
			return range[1] - range[0] <= 1;
		}
	}
	
	static class SegmentTree {
		Segment root;
		
		public SegmentTree(int min, int max) {
			this.root = new Segment(min, max);
		}
		
		public void add(int num) {
			add(this.root, num);
		}
		
		private void add(Segment segm, int num) {
			segm.count ++;
			if (!segm.isLeaf()) {
				int mid = (segm.range[0] + segm.range[1] ) / 2;
				if (segm.left == null) {
					segm.left = new Segment(segm.range[0], mid);// we are NOT including right boundary
				}
				if (segm.right == null) {
					segm.right = new Segment(mid, segm.range[1]);
				}
				if (inRange(num, segm.left.range)) {
					add(segm.left, num);
				}else if (inRange(num, segm.right.range)) {
					add(segm.right, num);
				}
			}else{
                System.out.println(num + "->" +segm);
            }
		}
		
		private boolean inRange(int num, int[] range) {
			return range[0] <= num && num < range[1]; 
		}
		
		public int query(int num) {
			int[] ans = new int[1];
			find(this.root, num, ans);
			return ans[0];
		}
		
		private void find(Segment segm, int num, int[] res) {
            if (segm == null){
                return;
            }
			if (inRange(num, segm.range)) {
				find(segm.left, num, res);
				find(segm.right, num, res);
			}else {
				if (num >= segm.range[1]) {// custom logic which solve the problem
					res[0] += segm.count;
				}
			}
		}
        
 	}
 

	public List<Integer> countSmaller(int[] nums) {

        List<Integer> result = new ArrayList<>();
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        
        SegmentTree tree = new SegmentTree(min, max + 1);

        for (int i = nums.length - 1; i >= 0; i--) {// from the last elem to the 1st one
        	result.add(tree.query(nums[i]));
        	tree.add(nums[i]);
        }
        Collections.reverse(result);
        return result;
    }

}
