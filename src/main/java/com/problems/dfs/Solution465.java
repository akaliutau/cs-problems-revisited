package com.problems.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A group of friends went on holiday and sometimes lent each other money. For
 * example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5
 * for a taxi ride. We can model each transaction as a tuple (x, y, z) which
 * means person x gave person y $z. Assuming Alice, Bill, and Chris are person
 * 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can
 * be represented as [[0, 1, 10], [2, 0, 5]]. Given a list of transactions
 * between a group of people, return the minimum number of transactions required
 * to settle the debt. Note: A transaction will be given as a tuple (x, y, z).
 * Note that x ≠ y and z > 0. Person's IDs may not be linear, e.g. we could have
 * the persons 0, 1, 2 or we could also have the persons 0, 2, 6. 
 * 
 * Example 1:
 * Input: [[0,1,10], [2,0,5]] Output: 2 
 * 
 * Explanation: Person #0 gave person #1
 * $10. Person #2 gave person #0 $5. Two transactions are needed. One way to
 * settle the debt is person #1 pays person #0 and #2 $5 each.
 * 
 * who       trans   balance    
 * ------------------------
 * Alice 0  -10 +5   -5
 * Bill  1  +10      +10 
 * Chris 2  -5       -5 
 * 
 *  
 * IDEA:
 *  
 */
public class Solution465 {

    int dfs(int index, List<Integer> list) {
        if (index == list.size()) {// end of list
            return 0;
        }
        // skip zero balance
        int balance = list.get(index); 
        if ( balance == 0) {
            return dfs(index + 1, list);// go to next balance
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = index + 1; i < list.size(); i++) {// index -> [index + 1 , n]
            if (list.get(i) * balance > 0) {// do not transfer if balance on one side, f.e. both in negatice zone or both lenders
                continue;
            }
            int temp = list.get(i);
            list.set(i, balance + list.get(i));// balance transfer (i.e. > 0, if balance < 0 and vice versa)
            ans = Math.min(ans, 1 + dfs(index + 1, list));// cur transfer + (get min)
            list.set(i, temp);// restore for backtracking

            // best solution, can be only 1
            // stop tracing when update fully compensates because consequent calls can only increase ans
            if (list.get(i) + balance == 0) {
                break;
            }
        }
        return ans;
    }

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();// map person => money
        // calculate final balance
        for (int[] transaction : transactions) {
            map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
            map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        return dfs(0, new ArrayList<>(map.values()));
    }

  

}
