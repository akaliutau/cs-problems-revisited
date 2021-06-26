package problem.segmenttree;

/**
 * Given an integer array nums, return the number of reverse pairs in the array.
 * 
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] >
 * 2 * nums[j].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,2,3,1] Output: 2
 * 
 * Example 2:
 * 
 * Input: nums = [2,4,3,5,1] Output: 3
 * 
 * IDEA: 
 * for each [i] we have to search all numbers at [i+1, end] looking for nums[i] > 2 * nums[j]
 * each found number (nums[i], nums[j])
 * 
 * We can use Segment Tree data structure to hold all numbers 2 * nums[i] seen so far,
 * and to query this tree against nums[i]
 * 
 * In technical:
 * use segment tree for 2 * [1,3,2,3,1], and standard query for num
 * 
 *
 */
public class Solution493 {

	static class Node {
		long[] range;
		int count = 0;
		Node left;
		Node right;

		public Node(long min, long max) {
			this.range = new long[] { min, max };
		}

		public boolean isLeaf() {
			return range[1] - range[0] <= 1l;
		}

	}

	static class SegmentTree {
		Node root;

		public SegmentTree(long min, long max) {
			this.root = new Node(min, max);
		}

		public void add(long num) {
			add(this.root, num);
		}

		private void add(Node node, long num) {
			node.count++;
			if (!node.isLeaf()) {
				long mid = (long) (node.range[0] + node.range[1]) / 2;
				if (node.left == null) {
					node.left = new Node(node.range[0], mid);
				}
				if (node.right == null) {
					node.right = new Node(mid, node.range[1]);
				}
				if (inRange(num, node.left.range)) {
					add(node.left, num);
				} else if (inRange(num, node.right.range)) {
					add(node.right, num);
				}
			}
		}

		private boolean inRange(long num, long[] range) {
			return range[0] <= num && num < range[1];
		}

		public int query(long num) {
			int[] ans = new int[1];
			find(this.root, num, ans);
			return ans[0];
		}

		private void find(Node node, long num, int[] res) {
			if (node == null) {
				return;
			}
			if (inRange(num, node.range)) {
				find(node.left, num, res);
				find(node.right, num, res);
			} else {
				if (num >= node.range[1]) {
					res[0] += node.count;
				}
			}
		}

	}

	public int reversePairs(int[] nums) {

		SegmentTree tree = new SegmentTree((long) 2 * Integer.MIN_VALUE, (long) 2 * Integer.MAX_VALUE);
		int ans = 0;
		for (int i = nums.length - 1; i >= 0; i--) {// from the last elem to the 1st one
			ans += tree.query(nums[i]);
			tree.add((long) 2 * nums[i]);
		}

		return ans;
	}
}
