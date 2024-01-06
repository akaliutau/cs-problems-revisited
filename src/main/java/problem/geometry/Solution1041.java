package problem.geometry;

/**
 * On an infinite plane, a point initially stands at (0, 0) and faces north. The
 * point can move in one of three direction:
 * 
 * "G": go straight 1 unit;
 * 
 * "L": turn 90 degrees to the left;
 * 
 * "R": turn 90 degrees to the right.
 * 
 * Return true if and only if there exists a circle in the plane such that the
 * point never leaves the circle after inf cycles.
 * 
 * IDEA: 
 * 
 * Let vector = {2,3}
 * 
 * 1. if vector == {0,0} then repeating does not change status quo
 * 
 * 2. if (vector != {0,0}, then check the direction
 * 
 *   old direction
 *     |
 *     |________  new direction	  
 *     |   /
 *     |  /
 *     | /
 *     |/
 *     
 *     
 *      
 */
public class Solution1041 {
	public boolean isRobotBounded(String instructions) {

		int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int x = 0, y = 0;
		int dir = 0;

		for (char i : instructions.toCharArray()) {
			if (i == 'L')
				dir = (4 + dir - 1) % 4;
			else if (i == 'R')
				dir = (dir + 1) % 4;
			else {
				x += directions[dir][0];
				y += directions[dir][1];
			}
		}

		return (x == 0 && y == 0) || (dir != 0);
	}
}
