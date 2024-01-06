package problem.intervals;

/**
 * Given an integer array nums, return the number of range sums that lie in
 * [lower, upper] inclusive. Range sum S(i, j) is defined as the sum of the
 * elements in nums between indices i and j (i ≤ j), inclusive. Note: A naive
 * algorithm of O(n2) is trivial. You MUST do better than that. 
 * 
 * Example: Input:
 * nums = [-2,5,-1], lower = -2, upper = 2, Output: 3 
 * 
 * Explanation: The three
 * ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 * Constraints: 0 <= nums.length <= 10^4 
 * 
 * IDEA: the second for loop in BF approach is used to
 * find the number of j that satisify: 
 * lower <= presum[i] - presum[j] <= upper 
 * ==> 
 * presum[i] - upper <= presum[j] <= presum[i] - lower 
 * 
 * We need to find out how many j satisifies this condition.
 * 
 * If we store the presum[] sorted, we can easily find the position of presum[i]
 * - upper and presum[i] - lower. 
 * 
 * So the number of satisified j will be count(pos2 - pos1) 
 * 
 * We could get the API from the code above: add(value): add
 * a value into a data structure query(): query how many numbers of values in
 * this range 
 * 
 * This is very close to Segment Tree
 */
public class Solution327 {

    public int countRangeSumBF(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            presum[i] = presum[i - 1] + nums[i - 1];
            for (int j = 0; j < i; j++) {
                if (lower <= presum[i] - presum[j] && presum[i] - presum[j] <= upper) {
                    res++;
                }
            }
        }
        return res;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        long val;
        int count = 1;
        
        TreeNode(long val) {
            this.val = val;
        }
    }
    
    TreeNode insert(TreeNode root, long num) {
        if (root == null) {
            return new TreeNode(num);
        }
        if (root.val == num) {
            root.count++;
        } else if (root.val > num) {
            root.count++;
            root.left = insert(root.left, num);
        } else {
            root.right = insert(root.right, num);
        }
        return root;
    }
    
    int find(TreeNode root, long num) {// use binary search
        if (root == null) {
            return 0;
        }
        if (root.val == num) {
            return root.count;
        } else if (num < root.val) {
            return find(root.left, num);
        } else {
            return root.count + find(root.right, num);
        }
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        TreeNode root = null;
        long[] sum = new long[nums.length + 1];
        sum[0] = 0L;
        root = insert(root, 0);
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        
        int res = 0;
        for (int i = 1; i < sum.length; i++) {
            long low = sum[i] - upper;
            long high = sum[i] - lower;
            
            res += (find(root, high) - find(root, low - 1));
            root = insert(root, sum[i]);
        }
        return res;
    }
}
