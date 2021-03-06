package com.problems.design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [41, 35, 62, 5, 97, 108] 
 * 
 * IDEA:
 * 1) to calculate median only centroid or central element is needed
 * 
 * 
 * Adding  41 MaxHeap lower: [41]         MinHeap higher: []            Median is 41 
 * 
 * Adding  35 MaxHeap lower: [35]         MinHeap higher: [41]          Median is 38
 * Adding  62 MaxHeap lower: [41, 35]     MinHeap higher: [62]          Median is 41 
 * Adding  4  MaxHeap lower: [35, 4]      MinHeap higher: [41, 62]      Median is 38 
 * Adding  97 MaxHeap lower: [41, 35, 4]  MinHeap higher: [62, 97]      Median is 41 
 * Adding 108 MaxHeap lower: [41, 35, 4]  MinHeap higher: [62, 97, 108] Median is 51.5
 * 
 * elem -> transfer through 2 heaps, 
 * lower - accumulates smaller elems
 * higher - accumulates bigger elems
 * 
 * 
 *         lower                       higher
 * (..., lower.peek()), median, (higher.peek(), ...)
 * 
 */
public class Solution295 {

    class MedianFinder {

        Queue<Integer> higher, lower;

        public MedianFinder() {
            higher = new PriorityQueue<>();// head contains the smallest elem in the set
            lower = new PriorityQueue<>((o, p) -> Integer.compare(p, o));// head contains the biggest elem in the set
        }

        public void addNum(int num) {
            higher.add(num);
            lower.add(higher.peek());

            higher.poll();

            if (higher.size() < lower.size()) {// balance to keep size ratio 50-50
                higher.add(lower.peek());
                lower.poll();
            }

        }

        public double findMedian() {
            return higher.size() > lower.size() ? higher.peek() : ((double) higher.peek() + lower.peek()) * 0.5;
        }
    }

  

}
