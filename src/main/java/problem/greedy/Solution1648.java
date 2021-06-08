package problem.greedy;

import java.util.Arrays;

/**
 * You have an inventory of different colored balls, and there is a customer
 * that wants orders balls of any color.
 * 
 * The customer weirdly values the colored balls. Each colored ball's value is
 * the number of balls of that color you currently have in your inventory. For
 * example, if you own 6 yellow balls, the customer would pay 6 for the first
 * yellow ball. After the transaction, there are only 5 yellow balls left, so
 * the next yellow ball is then valued at 5 (i.e., the value of the balls
 * decreases as you sell more to the customer).
 * 
 * You are given an integer array, inventory, where inventory[i] represents the
 * number of balls of the ith color that you initially own. You are also given
 * an integer orders, which represents the total number of balls that the
 * customer wants. You can sell the balls in any order.
 * 
 * Return the maximum total value that you can attain after selling orders
 * colored balls. As the answer may be too large, return it modulo 109 + 7.
 * Input: inventory = [2,5], orders = 4 Output: 14 
 * 
 * Explanation: Sell the 1st
 * color 1 time (2) and the 2nd color 3 times (5 + 4 + 3). The maximum total
 * value is 2 + 5 + 4 + 3 = 14.
 * 
 * IDEA:
 * 
 */
public class Solution1648 {

	public int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        int i = inventory.length - 1;
        int ordersLeft = orders, curr = inventory[i];
        long sum = 0;
        while (ordersLeft > 0) {
            while (i > 0 && inventory[i - 1] == curr) {
                --i;
            }
            int prev = (i == 0 ? 0 : inventory[i - 1]);
            int need = ordersLeft / (inventory.length - i);
            if (need == 0) {
                sum += (long) curr * ordersLeft;
                ordersLeft = 0;
            } else {
                int min = Math.min(need, curr - prev);
                sum += (curr - min + 1L + curr) * min / 2 * (inventory.length - i);
                ordersLeft -= min * (inventory.length - i);
                curr -= min;
            }
        }

        return (int)(sum % 1000000007);    
        
    }
}
