package com.problems.design;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Design
 */
public class Solution362 {

    class HitCounter {

        Map<Integer, Integer> lookUp;

        /** Initialize your data structure here. */
        public HitCounter() {
            lookUp = new ConcurrentHashMap<>();
        }

        /**
         * Record a hit.
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {

            lookUp.put(timestamp, lookUp.getOrDefault(timestamp, 0) + 1);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {

            int ctr = 0;

            int lowerBoundary = Math.max(0, timestamp - 300);
            // all the hits that is less than equal to 300. return count.
            for (int key : lookUp.keySet()) {
                if (key > lowerBoundary) {
                    ctr += lookUp.get(key);
                }else {
                    lookUp.remove(key); // clearing off the old hit records.
                }
            }
            return ctr;
        }
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
