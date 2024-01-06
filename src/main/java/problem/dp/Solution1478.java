package problem.dp;

import java.util.Arrays;

/**
 * Given the array houses and an integer k. where houses[i] is the location of
 * the ith house along a street, your task is to allocate k mailboxes in the
 * street. Return the minimum total dp between each house and its nearest
 * mailbox. The answer is guaranteed to fit in a 32-bit signed integer.
 */
public class Solution1478 {

    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        if (n == 1 || k == n)
            return 0;
        Arrays.sort(houses);

        // dist(x,y), When there is only 1 mailbox from House x to House y ,
        // people in House x to y need to walk at a sum of G(x,y);

        int[][] dist = new int[n][n];
        for (int x = 0; x < n; x++)
            for (int y = x; y < n; y++)
                dist[x][y] = x == y ? 0 : dist[x][y - 1] + houses[y] - houses[(x + y) / 2];

 
        int[][] dpSum = new int[k][n];
        for (int l = 0; l < n + 1 - k; l++)
            dpSum[0][l] = dist[0][l];

        for (int a = 1; a < k; a++)
            for (int l = 0; l < n + a - k + 1; l++) {
                int min = Integer.MAX_VALUE;
                for (int i = a - 1; i < l; i++)
                    min = Math.min(min, dpSum[a - 1][i] + dist[i + 1][l]);
                dpSum[a][l] = min;
            }

        return dpSum[k - 1][n - 1];

    }

}
