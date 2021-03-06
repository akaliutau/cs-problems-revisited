package com.problems.combinatorics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class Solution1010 {

    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int duration = time[i] % 60;
            duration = duration == 0 ? 60 : duration;
            freq.computeIfAbsent(duration, k -> 0);
            freq.put(duration, freq.get(duration) + 1);
        }
        Set<Integer> all = freq.keySet();

        int counter = 0;
        Set<Integer> processed = new HashSet<>();
        for (int d : all) {
            if (processed.contains(d)) {
                continue;
            }
            if (d == 30) {
                int k = freq.get(d);
                counter += k * (k - 1) / 2; // Possible Combination are: n*(n-1)/2
            } else if (d == 60) {
                int k = freq.get(d);
                counter += k * (k - 1) / 2;
            } else {
                int k1 = freq.get(d);
                int d2 = 60 - d;
                if (freq.containsKey(d2)) {
                    int k2 = freq.get(d2);
                    counter += k1 * k2;
                    processed.add(d2);
                }
            }
            processed.add(d);
        }
        return counter;
    }


}
