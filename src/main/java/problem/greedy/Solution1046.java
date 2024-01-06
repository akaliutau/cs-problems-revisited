package problem.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a collection of stones, each stone has a positive integer weight.
 * Each turn, we choose the two heaviest stones and smash them together. Suppose
 * the stones have weights x and y with x <= y. The result of this smash is: If
 * x == y, both stones are totally destroyed; If x != y, the stone of weight x
 * is totally destroyed, and the stone of weight y has new weight y-x. At the
 * end, there is at most 1 stone left. Return the weight of this stone (or 0 if
 * there are no stones left.) 
 * 
 * Example 1: Input: [2,7,4,1,8,1] Output: 1
 * Explanation: We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1]
 * then, we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then, we
 * combine 2 and 1 to get 1 so the array converts to [1,1,1] then, we combine 1
 * and 1 to get 0 so the array converts to [1] then that's the value of last
 * stone.
 * 
 * IDEA:
 * use queue with ordering by weight (PQ)
 * 
 */
public class Solution1046 {

    public int lastStoneWeight(int[] stones) {

        // Insert all the stones into a Max-Heap: NOTE by default the heap contains min element on its top
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            heap.add(stone);
        }

        // While there is more than one stone left, we need to remove the two largest
        // and smash them together. If there is a resulting stone, we need to put into
        // the heap.
        while (heap.size() > 1) {
            int stone1 = heap.remove();// the heaviest
            int stone2 = heap.remove();
            if (stone1 != stone2) {
                heap.add(stone1 - stone2);
            }
        }

        // Check whether or not there is a stone left to return.
        return heap.isEmpty() ? 0 : heap.remove();// last stone
    }

 
}
