package problem.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a hit counter which counts the number of hits received in the past 5
 * minutes.
 * 
 * Each function accepts a timestamp parameter (in seconds granularity) and you
 * may assume that calls are being made to the system in chronological order
 * (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 * 
 * It is possible that several hits arrive roughly at the same time.
 * 
 * Example:
 * 
 * HitCounter counter = new HitCounter();
 * 
 * // hit at timestamp 1. counter.hit(1);
 * 
 * // hit at timestamp 2. counter.hit(2);
 * 
 * // hit at timestamp 3. counter.hit(3);
 * 
 * // get hits at timestamp 4, should return 3. counter.getHits(4);
 * 
 * // hit at timestamp 300. counter.hit(300);
 * 
 * // get hits at timestamp 300, should return 4. counter.getHits(300);
 * 
 * // get hits at timestamp 301, should return 3. counter.getHits(301);
 * 
 * IDEA:
 * 1) use synchronized method to collect statistics
 * 2) count only those distance to the current moment is less than 300; remove all the oldest ones
 * 
 */
public class Solution362 {

	class HitCounter {

		Map<Integer, Integer> lookUp;

		public HitCounter() {
			lookUp = new HashMap<>();
		}

		/**
		 * Record a hit.
		 * 
		 * @param timestamp - The current timestamp (in seconds granularity).
		 */
		public synchronized void hit(int timestamp) {
			lookUp.compute(timestamp, (k, v) -> v == null ? 1 : v + 1);// EDGE case:  hits arrive roughly at the same time, then value in lookUp map > 1
		}

		/**
		 * Return the number of hits in the past 5 minutes.
		 * 
		 * @param timestamp - The current timestamp (in seconds granularity).
		 */
		public int getHits(int timestamp) {

			int ctr = 0;
			// calculated the oldest value for timestamp
			int lowerBoundary = Math.max(0, timestamp - 300);
			// all the hits that is less than equal to 300. return count.
			for (int time : lookUp.keySet()) {
				if (time > lowerBoundary) {// events occured after moment in the past @ (t - 300)
					ctr += lookUp.get(time);
				} else {
					lookUp.remove(time); // purge old hit records.
				}
			}
			return ctr;
		}
	}

	

}
