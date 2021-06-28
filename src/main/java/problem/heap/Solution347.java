package problem.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2]
 * 
 * IDEA:
 * 1. use map for collecting stat and PriorityQueue to query the result
 * 2. use partitioning
 * 
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

		Map<Integer, Elem> map = new HashMap<>();
		for (int num : nums) {
			map.computeIfAbsent(num, n -> new Elem(n)).freq++;
		}
		List<Elem> lst = new ArrayList<>(map.values());
		int low = Integer.MAX_VALUE;
		int high = 0;
		for (Elem elem : lst) {
			low = Math.min(low, elem.freq);
			high = Math.max(high, elem.freq);
		}
		List<Elem> found = new ArrayList<>();
        int count = k;
		while (count > 0) {
		    List<Elem> left = new ArrayList<>();
		    List<Elem> right = new ArrayList<>();
		    List<Elem> center = new ArrayList<>();
			int mid = (low + high) / 2;
			for (Elem elem : lst) {
				if (elem.freq < mid) {
					left.add(elem);
				}else if (elem.freq > mid) {
					right.add(elem);
				}else{
                    center.add(elem);
                }
			}
			if (right.size() <= count) {
				found.addAll(right);
				count -= right.size();
				high = mid;
				lst = left;
			}else {
				low = mid;
				lst = right;
			}
			
            lst.addAll(center);
            if (count == center.size()){// edge case - we will fail to divide central block, and there is ambiguity in determine the exact covergage
                found.addAll(center);
                count = 0;
            }
		}
		
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = found.get(i).num;
		}

		return res;

	}

}
