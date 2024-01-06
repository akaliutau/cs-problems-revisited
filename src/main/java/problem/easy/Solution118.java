package problem.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 */
public class Solution118 {
	
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        for (int r = 1; r < numRows; r++){
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = ans.get(r - 1);
            int n = prevRow.size();
            for (int i = 0; i < n + 1; i++){
                int left = i == 0 ? 0 : prevRow.get(i - 1);
                int right = i == n ? 0 : prevRow.get(i);
                row.add(left + right);
            }
            ans.add(row);
        }
        
        return ans;
    }

}
