package problem.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array arr of positive integers, call a (contiguous, not necessarily
 * distinct) subarray of arr good if the number of different integers in that
 * subarray is exactly K.
 * 
 * (For example, [1,2,3,1,2] has 3 diffCount integers: 1, 2, and 3.)
 * 
 * Return the number of good subarrays of arr.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [1,2,1,2,3], K = 2 Output: 7 Explanation: Subarrays formed with
 * exactly 2 diffCount integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2]
 * 
 * IDEA:
 * consider 2 sliding windows with end at the same point
 * 
 * We'll maintain two sliding windows, corresponding to left1, left2. Each sliding window will be able to stat 
 * how many diffCount elements there are in the window, and add and remove elements in a queue-like fashion.
 * 
 * Create two windows with a 'given end ptr' --> Note, this is very important. End ptr will always be fixed and same for the two windows in any given iteration.
 * i. 1st window is where the startPtr points such that the given subArray has <= K distinct integers
 * ii. 2nd window is where the startPtr points such that the given subArray has < K distinct integers (needed to remove all sunarrays smaller than K)
 * 
 * 2nd always < 1st by size
 * 
 * IMPORTANT - For a given array of length N and 'always ending with last element', number of possible sub-arrays = N
 * i. Number of possible sub-arrays of first window = N (with <= K distinct integers)
 * ii. Number of possible sub-arrays of second window = M (with < K distinct integers)
 * 
 * Total number of sub-arrays with 'exactly' K distinct integers = N - M,
 * 
 * since N = endPtr - startPtr1
 * and M = endPtr - startPtr2
 * 
 *  => N - M = startPtr2 - startPtr1
 * Continue doing this till endPtr iterates from start till end of the input array
 * 
 * 
 *  |			 left1
 * [1,2,1,2,3]
 *  |            left2
 *  
 * 
 *  |            left1
 * [1,2,1,2,3]
 *    |          left2
 *    
 * 
 *    |          left1
 * [1,2,1,2,3]
 *  |            left2
 *  
 * 
 *  |            left1
 * [1,2,1,2,3]
 *  |            left2
 * 
 * 
 */

public class Solution993 {

	static class Window {
		Map<Integer, Integer> stat= new HashMap<>();
		int diff = 0;

		void add(int x) {
			stat.put(x, stat.getOrDefault(x, 0) + 1);
			if (stat.get(x) == 1)
				diff++;
		}

		void remove(int x) {
			stat.put(x, stat.get(x) - 1);
			if (stat.get(x) == 0)
				diff--;
		}

		int diffCount() {
			return diff;
		}
	}

	public int subarraysWithKDistinct(int[] arr, int k) {
		Window window1 = new Window();
		Window window2 = new Window();
		int ans = 0, left1 = 0, left2 = 0;

		for (int right = 0; right < arr.length; ++right) {
			int x = arr[right];
			window1.add(x);
			window2.add(x);

			while (window1.diffCount() > k) {// repeat until stat contains = k
				int cur = arr[left1++];
				window1.remove(cur);
			}
			while (window2.diffCount() >= k) {// repeat until stat contains < k
				int cur = arr[left2++];
				window2.remove(cur);
			}

			ans += left2 - left1;
		}

		return ans;
	}

}
