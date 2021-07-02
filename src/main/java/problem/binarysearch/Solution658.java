package problem.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array arr, two integers k and x, find the k closest elements
 * to x in the array. The result should also be sorted in ascending order. If
 * there is a tie, the smaller elements are always preferred.
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [1,2,3,4,5], k = 4, x = 3 Output: [1,2,3,4]
 * 
 * IDEA:
 * the point is to find such mid that is the center of [mid, mid + k] - this will be the answer most close to the ideal one
 * detail example:
 * consider the original array and we have to find a smaller (of the same) window  
 * 
 * all possible windows of size 3:
 * [1,2,3,4,5]
 * [1,2,3]
 *   [2,3,4]      <--- the best one, as 3 is near center of window => add ths array as a result
 *     [3,4,5]
 * 
 * 
 * [1,2,3,4,5, 6] [1,3]
 *  |   |
 *  |  diff = -1 
 *  diff= 3
 * 
 *  0 1 2 3 4 5 6 7 8
 * [1,2,3,4,5,6,7,8,9]
 *  |   |   |
 *  |  mid  r=n-4
 * left
 * 
 */
public class Solution658 {

	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int n = arr.length;
		int left = 0;
		int right = n - k;
		List<Integer> ans = new ArrayList<>();
		if (n == 0 || n == 1) {
			return Arrays.stream(arr).boxed().collect(Collectors.toList());
		}
	    
		// try to find the ideal interval using binary search dividing
		while (left < right) {
			int lAnswer = (left + right) / 2;
			// investigate an array [lAnswer, lAnswer + k] : 
			// check distance from x to left boundary (x - arr[lAnswer])
			// check distance from x to right boundary (arr[lAnswer + k] - x)
			if (x - arr[lAnswer] > arr[lAnswer + k] - x) {
				left = lAnswer + 1;
			} else {
				right = lAnswer;
			}
		}

	    for (int i = left; i < left + k; i++) {
		    ans.add(arr[i]);
		}
		return ans;
	}

}
