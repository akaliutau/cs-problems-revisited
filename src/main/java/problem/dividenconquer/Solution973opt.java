package problem.dividenconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * We have a list of points on the plane. Find the K dist points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in.)
 * 
 * Example 1:
 * 
 * Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]] Explanation: The
 * distance between (1, 3) and the origin is sqrt(10). The distance between (-2,
 * 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to
 * the origin. We only want the dist K = 1 points from the origin, so the
 * answer is just [[-2,2]]
 * 
 * IDEA: use partitioning by the property of distance to the (0,0)
 * 
 * [3,3,5,4]
 *  | |
 *  
 * 
 */
public class Solution973opt {
	
	static int dist(int[] x) {
		return x[0] * x[0] + x[1] * x[1];
	}

	static void find(List<int[]> points, int k, List<int[]> res) {
		int n = points.size();
		if (n == 0 || k == 0) {
			return;
		}
		int mid = n / 2;
		int[] pivot = points.get(mid);
		
		List<int[]> closer = new ArrayList<>();
		List<int[]> farer = new ArrayList<>();

		for (int i = 0; i < n; i++) {// Divide all range on 2 parts:
			if (i == mid) {          // closer and farer
				continue;
			}
			int[] p = points.get(i);
			if (dist(p) <= dist(pivot)) {// closer than pivot
				closer.add(p);
			}else {
				farer.add(p);
			}
		}
		// NOTE: we have not added pivot yet
		// closer | pivot | farer
		
		if (k == closer.size() + 1) {
			res.add(pivot);
			res.addAll(closer);
			return;
		} else if (k < closer.size()) {// found more than needed
			find(closer, k, res);
		} else {                       // found less than needed
			res.addAll(closer);        // intermediate ans
            farer.add(pivot); // <-- will loose pivot if not add
			find(farer,  k - closer.size(), res);// NOTE: 
		}
	}

	public int[][] kClosest(int[][] points, int k) {
		List<int[]> arr = new ArrayList<>();
		for (int[] p : points) {
			arr.add(p);
		}
		int[][] res = new int[k][2];
		List<int[]>  acc = new ArrayList<>();
        if (k == points.length){
		    for (int i = 0; i < k; i++) {
			    res[i] = arr.get(i);
		    }
		    return res;
        }
		find(arr, k, acc);
		for (int i = 0; i < k; i++) {
			res[i] = acc.get(i);
		}
		return res;
	}

}
