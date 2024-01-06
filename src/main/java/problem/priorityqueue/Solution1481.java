package problem.priorityqueue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 * i.e. after minimizing the number of unique elements
 * 
 * IDEA:
 * consider the case:
 * [5,5,5,4,4,3], k = 2
 * 
 * statistics:
 * 
 * 5 => 3
 * 4 => 2
 * 3 => 1
 * 
 * 1. start removing elements with the smallest value of counter, i.e. with 1 which are unique
 * 2. 
 * 
 */
public class Solution1481 {
  	static class Holder {
		public int counter = 0;
		public int num;

		public Holder(int num) {
			this.num = num;
		}

		@Override
		public String toString() {
			return "Holder [counter=" + counter + ", num=" + num + "]";
		}
	}

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
               int n = arr.length;
        Map<Integer, Holder> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
        	if (!map.containsKey(arr[i])) {
        		map.put(arr[i], new Holder(arr[i]));
        	}
        	map.get(arr[i]).counter ++;
        }
        PriorityQueue<Holder> pq = new PriorityQueue<>((o,p) -> o.counter - p.counter);
        pq.addAll(map.values());
        int removed = 0;
        while(!pq.isEmpty()) {
        	k -= pq.poll().counter;
        	if (k < 0) {
        		break;
        	}
        	removed ++;
        }
        return map.size() - removed;

    }    
}
