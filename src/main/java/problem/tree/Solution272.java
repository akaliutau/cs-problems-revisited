package problem.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import problem.model.TreeNode;

/**
 * Given the root of a binary search tree, a target value, and an integer k,
 * return the k values in the BST that are closest to the target. You may return
 * the answer in any order.
 * 
 * IDEA:
 * Use PriorityQueue as a filter of size k which will accumulate only the closest elements
 * and filter out all the distant ones
 * Note the importance of in-order traversing:
 *
 */
public class Solution272 {

	void inorder(TreeNode r, List<Integer> nums, Queue<Integer> pq, int k) {
        if (r == null) 
            return;
                    
        inorder(r.left, nums, pq, k);
        pq.add(r.val);
        if (pq.size() > k) {// at the end PQ will accumulate only the closest elements
        	pq.remove();
        }
        inorder(r.right, nums, pq, k);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> nums = new ArrayList<>();
        
        // heap: 'more distant element is on the top'
        Queue<Integer> heap = new PriorityQueue<>((o, p) -> Math.abs(o - target) > Math.abs(p - target) ? -1 : 1);
        inorder(root, nums, heap, k);
        
        return new ArrayList<>(heap);
    }

}
