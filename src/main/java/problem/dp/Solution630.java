package problem.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There are n different online courses numbered from 1 to n. You are given an
 * array courses where courses[i] = [durationi, lastDayi] indicate that the ith
 * course should be taken continuously for durationi days and must be finished
 * before or on lastDayi.
 * 
 * You will start on the 1st day and you cannot take two or more courses
 * simultaneously.
 * 
 * Return the maximum number of courses that you can take.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]] Output: 3
 * Explanation: There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the
 * 100th day, and ready to take the next course on the 101st day. Second, take
 * the 3rd course, it costs 1000 days so you will finish it on the 1100th day,
 * and ready to take the next course on the 1101st day. Third, take the 2nd
 * course, it costs 200 days so you will finish it on the 1300th day. The 4th
 * course cannot be taken now, since you will finish it on the 3300th day, which
 * exceeds the closed date.
 * 
 */
public class Solution630 {
	
	static int key(int day, int duration) {
		return 10001 * day + duration;
	}

	int schedule(int[][] courses, int day, int time, Map<Integer,Integer> memo) {
		if (day == courses.length) {
			return 0;
		}
		int key = key(day, time);
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		int taken = 0;
		if (time + courses[day][0] <= courses[day][1]) {
			taken = 1 + schedule(courses, day + 1, time + courses[day][0], memo);
		}
		int notTaken = schedule(courses, day + 1, time, memo);
		memo.put(key, Math.max(taken, notTaken));
		return memo.get(key);
	}
	
	public int scheduleCourse(int[][] courses) {
		Arrays.sort(courses, (o,p) -> o[1] - p[1]);
		Map<Integer,Integer> memo = new HashMap<>();
		return schedule(courses, 0, 0, memo);
	}

}
