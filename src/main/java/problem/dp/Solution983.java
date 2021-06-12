package problem.dp;

/**
 * In a country popular for train travel, you have planned some train traveling
 * one year in advance. The days of the year that you will travel is given as an
 * array days. Each day is an integer from 1 to 365. Train tickets are sold in 3
 * different ways: a 1-day pass is sold for costs[0] dollars; a 7-day pass is
 * sold for costs[1] dollars; a 30-day pass is sold for costs[2] dollars. The
 * passes allow that many days of consecutive travel. 
 * 
 * For example, if we get a
 * 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8. 
 * 
 * Return the minimum number of dollars you need to travel every day in the
 * given list of days
 */
public class Solution983 {

    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[] { 1, 7, 30 };
    
    int cost(int i) {
        if (i >= days.length) {
            return 0;
        }
        if (memo[i] != null) {
            return memo[i];
        }

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k]) {
                j++;
            }
            ans = Math.min(ans, cost(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];

        return cost(0);
    }

    

}
