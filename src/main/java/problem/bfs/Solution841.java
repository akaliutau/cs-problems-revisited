package problem.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * There are N rooms and you start in room 0. Each room has a distinct number in
 * 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * 
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j]
 * is an integer in [0, 1, ..., N-1] where N = rooms.length. A key rooms[i][j] =
 * v opens the room with number v.
 * 
 * Initially, all the rooms start locked (except for room 0).
 * 
 * You can walk back and forth between rooms freely.
 * 
 * Return true if and only if you can enter every room.
 * 
 * Example 1:
 * 
 * Input: [[1],[2],[3],[]] Output: true 
 * 
 * Explanation: We start in room 0, and
 * pick up key 1. We then go to room 1, and pick up key 2. We then go to room 2,
 * and pick up key 3. We then go to room 3. Since we were able to go to every
 * room, we return true.
 * 
 * IDEA:
 * 1. start from initial room
 * 2. get the keys, go to all rooms we currently have keys, repeat
 * 
 */
public class Solution841 {
	public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
		int nRooms = rooms.size();
		boolean[] visited = new boolean[nRooms];
		int nVisited = 1;

		Queue<Integer> toVisit = new LinkedList<>();
		toVisit.add(0);
		visited[0] = true;

		while (!toVisit.isEmpty()) {
			int room = toVisit.poll();
			for (Integer key : rooms.get(room)) {
				if (!visited[key]) {// avoid visiting the same room once again 
					visited[key] = true;
					nVisited++;
					toVisit.add(key);
				}
			}
		}
		return nVisited == nRooms;
	}

}
