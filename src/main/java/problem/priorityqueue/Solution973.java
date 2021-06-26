package problem.priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane. Find the K closest points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in.)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]] Explanation: The
 * distance between (1, 3) and the origin is sqrt(10). The distance between (-2,
 * 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to
 * the origin. We only want the closest K = 1 points from the origin, so the
 * answer is just [[-2,2]]
 * 
 * IDEA:
 * option1. use a data structure with ordering by distance to the point (0,0)
 * option2. use partitioning
 * 
 */
public class Solution973 {

	static class Point {
		public int[] p;
		public int dist;

		public Point(int[] p) {
			this.p = p;
			this.dist = (int) Math.sqrt(p[0] * p[0] + p[1] * p[1]);
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


}
