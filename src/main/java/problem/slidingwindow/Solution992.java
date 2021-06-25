package problem.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given an array nums of positive integers, call a (contiguous, not necessarily
 * distinct) subarray of nums good if the number of unique integers in that
 * subarray is exactly k.
 * 
 * (For example, [1,2,3,1,2] has 3 unique integers: 1, 2, and 3. So it's good for k = 3)
 * 
 * Return the number of good subarrays of nums.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,1,2,3], k = 2 Output: 7 
 * 
 * Explanation: Subarrays formed with exactly 2 unique integers: 
 * 
 * [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 
 * IDEA:
 * 1. use 2 stateful windows which can track the statistics about numbers seen so far
 * [1,2,1,2,3]
 *  |     |  \   
 *  |     |    right
 *  |    win2.left 
 *  win1.left
 *  
 *  2. in the beginning when unique < k both arrays grow equally
 *  3. when array includes k unique numbers, the 2nd window starts to shrink, and continue to do that when # of unique numbers > k
 *  4. now we have
 *     win1:[left, right] - all arrays with unique <= k
 *     win2:[left, right] - all arrays with unique < k
 *     
 *  5. It is clear that win2 is a subset of win1, and difference defines the number of arrays with unique = k
 *  
 *    win1     |   win2    |  gain
 *  -------------------------------
 *    [1]          [1]         0
 *    [1,2]        [2]         1
 *    [1,2,1]      [1]         2
 *    [1,2,1,2]    [2]         3
 *    [2,3]        [3]         1
 *    
 *   6. Number of different arrays seen so far:
 *    [1,2,3]
 *    [1]                <-- length of current array = 1
 *    [2] [1,2]          <-- length of current array = 2
 *    [3] [2,3] [1,2,3]  <-- length of current array = 3
 *    
 *    ============
 *    total arrays = 1+ 2+ 3 = 6
 *    
 *    
 *   7. sum1 = [id1, id2, id3]      <-- arrays with feature uniques <= k
 *      sum2 = [id2, id3]           <-- arrays with feature uniques < k
 *      sum1 - sum2 = [id1, id2, id3] - [id2, id3] = [id1]    <-- 
 *  
 *    
 */
public class Solution992 {
	static class Window {
		Map<Integer, Integer> stat= new HashMap<>();
		int unique = 0;
		int left = 0; 
		int right = 0;

		void add(int[] arr) {
			int num = arr[right++];
			stat.compute(num, (k,v) -> v == null ? 1 : v + 1 );
			if (stat.get(num) == 1) {
				unique++;
			}
		}

		void remove(int[] arr) {
			int num = arr[left++];
			stat.compute(num, (k,v) -> v - 1 );
			if (stat.get(num) == 0) {
				unique--;
			}
		}

	}

	public int subarraysWithKDistinct(int[] arr, int k) {
		Window window1 = new Window();
		Window window2 = new Window();
		int sum1 = 0;
		int sum2 = 0;

		for (int right = 0; right < arr.length; ++right) {
			window1.add(arr);
			window2.add(arr);

			while (window1.unique > k) {// stops when window contains unique <= k
				window1.remove(arr);
			}
			while (window2.unique >= k) {// stops when window contains number of unique numbers strictly < k
				window2.remove(arr);
			}
			//     # of arrays with unique > k
			sum1 += (window1.right - window1.left);
			sum2 += (window2.right - window2.left);
		}

		return sum1 - sum2;
	}

}
