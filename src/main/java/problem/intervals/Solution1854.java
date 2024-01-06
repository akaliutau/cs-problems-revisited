package problem.intervals;

/**
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi]
 * indicates the birth and death years of the ith person.
 * 
 * The population of some year x is the number of people alive during that year.
 * The ith person is counted in year x's population if x is in the inclusive
 * range [birthi, deathi - 1]. Note that the person is not counted in the year
 * that they die.
 * 
 * Return the earliest year with the maximum population.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: logs = [[1993,1999],[2000,2010]] Output: 1993 Explanation: The maximum
 * population is 1, and 1993 is the earliest year with this population.
 * 
 * IDEA: build the accumulative histogram
 * 
 * Follow up: for big range id does make sense to use a slightly different approach: 
 * sort all intervals by their start year and track the ending using counter 
 *
 */
public class Solution1854 {

	public int maximumPopulation(int[][] logs) {
		int[] years = new int[101];
		for (int[] pair : logs) {
			int start = pair[0] - 1950;
			int end = pair[1] - 1950;
			for (int i = start; i < end; i++) {
				years[i]++;
			}
		}
		int maxYear = 0;
		int population = 0;
		for (int i = 0; i < 101; i++) {
			if (population < years[i]) {
				maxYear = 1950 + i;
				population = years[i];
			}
		}
		return maxYear;
	}
}
