package com.problems.greedy;

import java.util.Arrays;

/**
 * Given a characters array tasks, representing the tasks a CPU needs to do,
 * where each letter represents a different task. Tasks could be done in any
 * order. Each task is done in one unit of time. For each unit of time, the CPU
 * could complete either one task or just be idle.
 * 
 * However, there is a non-negative integer n that represents the cooldown
 * period between two same tasks (the same letter in the array), that is that
 * there must be at least n units of time between any two same tasks.
 * 
 * Return the least number of units of times that the CPU will take to finish
 * all the given tasks
 * 
 * IDEA:
 * 
 * The total number of CPU intervals we need consists of busy and idle slots.
 * Number of busy slots is defined by the number of tasks to execute:
 * len(tasks). The problem is to compute a number of idle slots.
 * 
 * Maximum possible number of idle slots is defined by the frequency of the most
 * frequent task: idle_time <= (f_max - 1) * n.
 * 
 */
public class Solution621 {

	public int leastInterval(char[] tasks, int n) {
		// frequencies of the tasks
		int[] frequencies = new int[26];
		for (int t : tasks) {
			frequencies[t - 'A']++;
		}

		Arrays.sort(frequencies);

		// max frequency - the last one
		int maxFreq = frequencies[25];
		int idle = (maxFreq - 1) * n;// max # of available (empty) slots

		for (int i = frequencies.length - 2; i >= 0 && idle > 0; --i) {
			idle -= Math.min(maxFreq - 1, frequencies[i]);// decrease the number of empty slots, because they will be filled by some task
		}
		idle = Math.max(0, idle);

		return idle + tasks.length;
	}

}
