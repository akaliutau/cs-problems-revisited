package problem.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set). Note: The solution set must not contain duplicate subsets.
 * Example: 
 * Input: nums = [1,2,3] 
 * Output: 
 * [
 * 
 *  [3], 
 *  [1], 
 *  [2], 
 *  
 *  [1,2,3], 
 *  
 *  [1,3],
 *  [2,3], 
 *  [1,2], 
 *  
 *  [] 
 *  
 * ]
 * 
 * IDEA:
 * [] -> [1], [2], [3] - generate initial set,
 * then use this set to append the next available number: 
 * [1] -> [1,2], [1,3], and so on
 * 
 */
public class Solution78 {

    List<List<Integer>> output = new ArrayList<>();
    int n;

    void backtrack(int first, int last, List<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == last) {
            output.add(new ArrayList<>(curr));
        }

        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integer to complete the combination
            backtrack(i + 1, last, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (int k = 0; k < n + 1; ++k) {
            backtrack(0, k, new ArrayList<>(), nums);
        }
        return output;
    }

}
