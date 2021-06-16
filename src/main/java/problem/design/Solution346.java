package problem.design;

import java.util.Arrays;

/**
 * 
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * 
 * Implement the MovingAverage class:
 * 
 * MovingAverage(int size) Initializes the object with the size of the window
 * size. double next(int val) Returns the moving average of the last size values
 * of the stream.
 * 
 */
public class Solution346 {
	static class MovingAverage {

		int[] values;
		int idx = 0;
		long sum = 0l;
		int counter = 0;

		public MovingAverage(int size) {
			values = new int[size];
			Arrays.fill(values, Integer.MAX_VALUE);
		}

		public double next(int val) {
			int last = values[idx];
			if (last != Integer.MAX_VALUE) {
				sum -= last;
			}
			sum += val;
			counter++;
			counter = Math.min(counter, values.length);
			values[idx] = val;
			idx++;
			idx = idx % values.length;
			return (double) sum / counter;
		}
	}

}
