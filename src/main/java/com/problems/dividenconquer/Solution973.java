package com.problems.dividenconquer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Divide and conquer, heap
 */
public class Solution973 {

    static class Point {
        public int[] p;
        public int dist;

        public Point(int[] p) {
            this.p = p;
            this.dist = p[0] * p[0] + p[1] * p[1];
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        List<Point> pointLi = new ArrayList<>();
        Comparator<Point> byDist = (o, p) -> Integer.compare(o.dist, p.dist);
        for (int[] p : points) {
            pointLi.add(new Point(p));
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(byDist);
        pq.addAll(pointLi);
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().p;
        }
        return res;
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
