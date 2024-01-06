package problem.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you
 * may move to adjacent numbers on the row below. For example, given the
 * following triangle 
 *      [ 
 *        [2], 
 *       [3,4], 
 *      [6,5,7], 
 *     [4,1,8,3] ] 
 *     
 * The minimum path sum
 * from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * IDEA: propogate the sums through all triangle
 */
public class Solution120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        List<Integer> prev = new ArrayList<>(triangle.get(0));
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = new ArrayList<>(triangle.get(i));
            int n = row.size();
            
            row.set(0, row.get(0) + prev.get(0));// only 1 path possible, f.e. 2 -> 3, OR 3 -> 6
            
            for (int j = 1; j < n - 1; j++) {
                row.set(j, row.get(j) + Math.min(prev.get(j - 1), prev.get(j)));// choose the smallest one
            }
            
            row.set(n - 1, row.get(n - 1) + prev.get(prev.size() - 1));// only 1 path possible
            
            prev = row;
        }
        return Collections.min(prev);

    }

}
