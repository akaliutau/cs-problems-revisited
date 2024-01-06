package problem.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The demons had captured the princess (P) and imprisoned her in the
 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the
 * princess. The knight has an initial health point represented by a positive
 * integer. If at any point his health point drops to 0 or below, he dies
 * immediately. Some of the rooms are guarded by demons, so the knight loses
 * health (negative integers) upon entering these rooms; other rooms are either
 * empty (0's) or contain magic orbs that increase the knight's health (positive
 * integers). In order to reach the princess as quickly as possible, the knight
 * decides to move only rightward or downward in each step. Write a function to
 * determine the knight's minimum initial health so that he is able to rescue
 * the princess.
 * 
 * IDEA: a classic BFS with limit 2 on update
 * 
 */
public class Solution174 {

    static int calcDiff(int minNeededLevel, int increase){
        return minNeededLevel <= increase ? 1 : minNeededLevel - increase;
    }
    
    public int calculateMinimumHP(int[][] dungeon) {
               int m = dungeon.length;
        int n = 0;
        if (m > 0) {
            n = dungeon[0].length;
        }
        int[][] counter = new int[m][n];
        int[][] inhealth = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(inhealth[i], Integer.MAX_VALUE);
        }
        inhealth[m - 1][n - 1] = dungeon[m - 1][n - 1] > 0 ? 1 : 1 - dungeon[m - 1][n - 1];; // min health at this point
        Queue<int[]> q = new LinkedList<>();
        // int[4] = {row, col, inHealth, outHealth}
        q.add(new int[] { m - 1, n - 1});
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int r = state[0];
            int c = state[1];
            if (r - 1 >= 0) {
                int minHealthNeeded = dungeon[r - 1][c] > 0 ? calcDiff(inhealth[r][c], dungeon[r - 1][c])  : inhealth[r][c] - dungeon[r - 1][c];
                inhealth[r - 1][c] = Math.min(inhealth[r - 1][c], minHealthNeeded);// set optimal
                if (counter[r - 1][c]++ > 2) {
                    q.add(new int[] { r - 1, c});
                }
            }
            if (c - 1 >= 0) {
                int minHealthNeeded = dungeon[r][c - 1] > 0 ? calcDiff(inhealth[r][c], dungeon[r][c - 1]) : inhealth[r][c] - dungeon[r][c - 1];
                inhealth[r][c - 1] = Math.min(inhealth[r][c - 1], minHealthNeeded);// set optimal
                if (counter[r][c - 1]++ > 2) {
                    q.add(new int[] { r, c - 1});
                }
            }
        }
        
        return inhealth[0][0];
 
    }
}
