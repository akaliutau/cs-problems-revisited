package problem.greedy;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N
 * <= M. Each worker and bike is a 2D coordinate on this grid. Our goal is to
 * assign a bike to each worker. Among the available bikes and workers, we
 * choose the (worker, bike) pair with the shortest Manhattan distance between
 * each other, and assign the bike to that worker. 
 * 
 * (If there are multiple
 * (worker, bike) pairs with the same shortest Manhattan distance, we choose the
 * pair with the smallest worker index; if there are multiple ways to do that,
 * we choose the pair with the smallest bike index). 
 * 
 * We repeat this process
 * until there are no available workers. The Manhattan distance between two
 * points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|. 
 * 
 * Return
 * a vector ans of length N, where ans[i] is the index (0-indexed) of the bike
 * that the i-th worker is assigned to 
 * 
 * Input: workers = [[0,0],[2,1]], bikes =
 * [[1,2],[3,3]] Output: [1,0] 
 * 
 * explanation: Worker 1 grabs Bike 0 as they are
 * closest (without ties), and Worker 0 is assigned to the Bike 1. So the output is [1, 0].
 * 
 * IDEA:
 * use data structure to dynamically track the closest Bike to the Worker
 * 1) generate all possible pairs
 * 2) the satisfactory candidate is always on the top 
 * 
 */
public class Solution1057 {

    static int dist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[] result = new int[n];
        Set<Integer> workersAssigned = new HashSet<>();
        Set<Integer> bikesAssigned = new HashSet<>();

        Comparator<int[]> byDist = (o, p) -> Integer.compare(o[0], p[0]);
        Comparator<int[]> byWorkerIndex = (o, p) -> Integer.compare(o[1], p[1]);
        Comparator<int[]> byBike = (o, p) -> Integer.compare(o[2], p[2]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(byDist.thenComparing(byWorkerIndex).thenComparing(byBike));

        // put all possible n^2 pairs into PQ
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int d = dist(workers[i], bikes[j]);
                pq.add(new int[] { d, i, j });
            }
        }

        while (workersAssigned.size() != n && bikesAssigned.size() != m) {
            int[] candidate = pq.poll();// the satisfactory candidate is always on the top 
            int worker = candidate[1];
            int bike = candidate[2];
            if (!workersAssigned.contains(worker) && !bikesAssigned.contains(bike)) {
                result[worker] = bike;
                workersAssigned.add(worker);
                bikesAssigned.add(bike);
            }
        }
        return result;
    }

  

}
