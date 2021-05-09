package problem.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
 */
public class Solution347 {

	static class Elem {
		public int freq = 0;
		public int num;

		public Elem(int num) {
			this.num = num;
		}

	}

	public int[] topKFrequent(int[] nums, int k) {
		int n = nums.length;
		Comparator<Elem> byFreq = (o, p) -> Integer.compare(p.freq, o.freq);
		PriorityQueue<Elem> queue = new PriorityQueue<>(n, byFreq);
		Map<Integer, Elem> map = new HashMap<>();
		for (int i : nums) {
			if (!map.containsKey(i)) {
				map.put(i, new Elem(i));
			}
			map.get(i).freq++;
		}
		queue.addAll(map.values());
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = queue.poll().num;
		}

		return res;

	}

	

}
