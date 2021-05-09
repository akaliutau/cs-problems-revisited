package problem.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A Stepping Number is an integer such that all of its adjacent digits have an
 * absolute difference of exactly 1. For example, 321 is a Stepping Number while
 * 421 is not. Given two integers low and high, find and return a sorted list of
 * all the Stepping Numbers in the range [low, high] inclusive. 
 * Example 1:
 * Input: low = 0, high = 21 Output: [0,1,2,3,4,5,6,7,8,9,10,12,21] 
 * 
 * IDEA:
 * 
 * BFS: start
 * node = 0 From 0, we can move to 1 2 3 4 5 6 7 8 9 
 * from 1 -> 12 and 10 
 * from 2 -> 23 and 21 
 * from 3 -> 34 and 32
 */
public class Solution1215 {

    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        if (low > high)
            return res;

        Queue<Long> queue = new LinkedList<>();
        for (long i = 1; i <= 9; i++) {
            queue.add(i);
        }

        if (low == 0) {
            res.add(0);
        }
        while (!queue.isEmpty()) {
           long p = queue.poll();
           if (p >= low && p <= high) {
               res.add((int)p);
           }
           // calc next
           long lastDigit = p % 10;
           if (lastDigit > 0) {// all but 0
               long cand = p * 10 + (lastDigit - 1);// calc lower number
               if (cand <= high)
                   queue.add(cand);
           }
           if (lastDigit < 9) {// all but 9
               long cand = p * 10 + (lastDigit + 1);// calc higher number
               if (cand <= high)
                   queue.add(cand);
           }
           
        }
        return res;
    }

 
}
