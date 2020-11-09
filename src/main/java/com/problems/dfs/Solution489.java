package com.problems.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a robot cleaner in a room modeled as a grid. Each cell in the grid can
 * be empty or blocked. The robot cleaner with 4 given APIs can move forward,
 * turn left or turn right. Each turn it made is 90 degrees. When it tries to
 * move into a blocked cell, its bumper sensor detects the obstacle and it stays
 * on the current cell. Design an algorithm to clean the entire room using only
 * the 4 given APIs shown below. 
 * 
 * Example: 
 * 
 * Input: room = 
 * [ 
 * [1,1,1,1,1,0,1,1],
 * [1,1,1,1,1,0,1,1], 
 * [1,0,1,1,1,1,1,1], 
 * [0,0,0,1,0,0,0,0], 
 * [1,1,1,1,1,1,1,1] 
 * ],
 * row = 1, col = 3 
 * 
 * Explanation: All grids in the room are marked by either 0 or
 * 1. 0 means the cell is blocked, while 1 means the cell is accessible. The
 * robot initially starts at the position of row=1, col=3. From the top left
 * corner, its position is one row below and three columns right.
 */
public class Solution489 {

    interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();

        void turnRight();

        // Clean the current cell.
        void clean();

    }

    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    Set<Long> visited = new HashSet<>();
    Robot robot;

    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    public void backtrack(int row, int col, int d) {
        long key = (long) Integer.MAX_VALUE * row + col;
        visited.add(key);
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int nr = row + directions[newD][0];
            int nc = col + directions[newD][1];
            long k = (long) Integer.MAX_VALUE * nr + nc;

            if (!visited.contains(k) && robot.move()) {
                backtrack(nr, nc, newD);
                goBack();
            }
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }

   

}
