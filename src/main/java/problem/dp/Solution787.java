package problem.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * There are n cities connected by some number of flights. You are given an
 * array flights where flights[i] = [fromi, toi, pricei] indicates that there is
 * a flight from city fromi to city toi with curCost pricei.
 * 
 * You are also given three integers src, dst, and k, return the cheapest price
 * from src to dst with at most k stopsSoFar. If there is no such route, return -1.
 * 
 * IDEA:
 * use Brute force with memoization
 * 1. start from the source
 * 2. 
 * 
 * 
 * 
 * Brute force:
 * V * (V-1) * (V-2) * ... * (V-k) ~ O(V^k)
 * 
 * 
 * V * k - cost of building of rebuilding memo table
 * 
 * When we build table, each cell will cost V iterations, => 
 * 
 * O(V * V * k) = O(V^2 * k)
 * 
 * 
 * Note: we are not using here a seen table, as this could filter out sub-paths:
 * 
 *  0 ----10----- 1
 *  |             |
 *  |             |
 *  100          100
 *  |             | 
 *  |             |
 *  2 ----20----- 3 ---15--- 5
 *  
 *  
 *  source = 0, we start building sub-paths from this point
 *  
 *  when we build sub-paths from 3, we have to investigate ALL neighbors regardless of they were visited or not
 *  (because we are building chunks, not continuous path)
 * 
 */
public class Solution787 {
    Integer[][] adj;
    Map<Long, Long> memo;
    
    
    public long dfs(int node, int stopsLeft, int dst, int n) {
        
        // No need to go any further if the destination is reached    
        if (node == dst) {
            return 0;
        }
        
        if (stopsLeft < 0) {
            return Integer.MAX_VALUE;
        }
            
        Long key = (long) (1000 * node + stopsLeft);
    
    
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // Recursive calls over all the nextNodes
        long ans = Integer.MAX_VALUE;
        
        for (int nextNode = 0; nextNode < n; nextNode++) {// investigate all neighbors
            Integer cost = adj[node][nextNode];
            // null means no connection
            if (cost != null) {
                ans = Math.min(
                				ans, 
                				dfs(nextNode, stopsLeft - 1, dst, n) + cost
                			);            
            }  
        } 
        // Cache the result
        this.memo.put(key, ans);
        return ans;
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        this.adj = new Integer[n][n];
        this.memo = new HashMap<>();
        
        for (int[] flight: flights) {
            this.adj[flight[0]][flight[1]] = flight[2];
        }
            
        long ans = dfs(src, k, dst, n);
        
        return ans >= Integer.MAX_VALUE ? -1 : (int)ans;
    }

}
