package problem.selected;

import java.util.LinkedList;
import java.util.Queue;

public class BoatMovements {
    public static boolean canTravelTo(boolean[][] gameMatrix, int fromRow, int fromColumn, int toRow, int toColumn) {
        int[] start = new int[]{fromRow, fromColumn};
        int[] end = new int[]{toRow, toColumn};
        int n = gameMatrix.length;
        int m = 0;
        if (gameMatrix.length > 0){
            m = gameMatrix[0].length;
        }
        boolean[][] seen = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        int[] dirs = new int[]{-1, 0, 1, 0, 0, -1, 0, 1};
        boolean canMove = (Math.abs(start[0] - end[0]) == 2 && Math.abs(start[1] - end[1]) == 0 ) ||
                (Math.abs(start[0] - end[0]) == 0 && Math.abs(start[1] - end[1]) == 1 );
        if (!canMove) return false;
        while (!q.isEmpty()){
            int[] pos = q.poll();
            if (pos[0] == end[0] && pos[1] == end[1]){
                return true;
            }
            for (int i = 0; i < 8; i+=2) {
                int[] next = new int[]{pos[0] + dirs[i], pos[1] + dirs[i+1]};
                if (next[0] >= 0 && next[0] < n && next[1] >= 0 && next[1] < m && gameMatrix[next[0]][next[1]]) {
                    if (seen[next[0]][next[1]]) continue;
                    q.add(next);
                    seen[next[0]][next[1]] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean[][] gameMatrix = {
                { false, false, true, true, false },
                { false, false, true, false, false },
                { false, false, true, true, false },
                { false, true, false, true, false },
                { false, false, true, false, false }
        };

        System.out.println(canTravelTo(gameMatrix, 2, 2, 0, 2));
        System.out.println(canTravelTo(gameMatrix, 2, 2, 2, 1));
        System.out.println(canTravelTo(gameMatrix, 2, 2, 2, 3));
        System.out.println(canTravelTo(gameMatrix, 2, 2, 4, 2));
    }
}
