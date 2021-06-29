package problem.math;

/**
 * 
 * calculate the smallest angel between watches hands
 *
 */
public class Solution1344 {
	public double angleClock(int hour, int minutes) {
		double hourAngStep = (double) hour * 360 / 12;
		double minutesAngStep = (double) minutes * 360 / 60;
		double hourCorrection = (double) (360 / 12) * minutes / 60;

		double diff = Math.abs(hourAngStep + hourCorrection - minutesAngStep);

		return diff > 180 ? 360 - diff : diff;

	}
}
