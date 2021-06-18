package problem.segmenttree;

/**
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive. The update(i, val) function modifies nums by
 * updating the element at index i to val. 
 * 
 * Example: Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9 update(1, 2) sumRange(0, 2) -> 8 
 * 
 * INFO:
 * 
 * Segment tree is a very
 * flexible data structure, because it is used to solve numerous range query
 * problems like finding minimum, maximum, sum, greatest common divisor, least
 * common denominator in array in logarithmic time. 
 * 
 * The segment tree for array
 * a[0, 1, ... ,n-1] is a binary tree in which each node contains aggregate
 * information (min, max, sum, etc.) for a subrange [i ... j] of the array, as
 * its left and right child hold information for range [i ... {i+j}/2] and
 * [{i+j}/2 + 1, j] 
 * 
 * Segment tree could be implemented using either an array or a
 * tree. For an array implementation, if the element at index ii is not a leaf,
 * its left and right child are stored at index 2i and 2i + 1 respectively.
 * 
 * IDEA:
 * Build a segment tree for [1, 3, 5]:
 * 
 *                 [1, 3, 5],9
 *                 /       \
 *            [1,3],4         [5].5
 *           /     \
 *         [1],1      [3],3       
 * 
 * On update:
 * 
 * 1. find the Node
 * 2. update and recalc all sums in the process (sum = init_sum - old + new)
 * 
 * O(log n)
 * 
 * On query:
 * 1. check the current node's range for intersection
 * 2. if there is intersection, go deeper
 * 
 */
public class Solution307 {
	
	class Node {
		int[] range;
		int sum = 0;
		Node left;
		Node right;
		
		public Node(int left, int right) {
			this.range = new int[] {left, right};// right index is inclusive !
		}
		
		public boolean isLeaf() {
			return range[0] == range[1];
		}
		
		public boolean inRange(int pos) {
			return range[0] <= pos && pos <= range[1];
		}
	}

	
    class NumArray {

    	Node tree;

        public NumArray(int[] nums) {
        	int n = nums.length;
        	tree = new Node(0, n - 1);
        	buildTree(tree, nums);
        }

        private void buildTree(Node node, int[] nums) {
            if (node.isLeaf()) {
            	node.sum = nums[node.range[0]];
            	return;
            }
            int mid = (node.range[0] + node.range[1]) / 2;
            node.left = new Node(node.range[0], mid);
            node.right = new Node(mid + 1, node.range[1]);
            buildTree(node.left, nums);
            buildTree(node.right, nums);
            node.sum = node.left.sum + node.right.sum;
        }

        void update(int pos, int val) {
        	update(null, tree, pos, val);
        }
        
        private void update(Node parent, Node node, int pos, int val) {
        	if (node == null) {
        		return;
        	}
        	if (node.inRange(pos)) {// ignore nodes if out of range
            	if (node.isLeaf()) {
            		node.sum = val;
        	    }else {
        	    	update(node, node.left, pos, val);
        	    	update(node, node.right, pos, val);
        	    	node.sum = node.left.sum + node.right.sum;
        	    }
        	}
        }

        public int sumRange(int l, int r) {
        	int[] res = new int[1];
        	sum(tree, l , r, res);
        	return res[0];
        }
        
        private void sum(Node node, int l, int r, int[] res) {
        	if (node == null || node.range[1] < l || node.range[0] > r) {
        		return;
        	}
            
        	if (l <= node.range[0] && node.range[1] <= r) {
        		res[0] += node.sum;
        		return;
        	}
        	sum(node.left, l ,r, res);
        	sum(node.right, l ,r, res);
        }
    }

}
