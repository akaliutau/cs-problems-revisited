package problem.misc;

/**
 * You are given a string time in the form of hh:mm, where some of the digits in
 * the string are hidden (represented by ?).
 * 
 * The valid times are those inclusively between 00:00 and 23:59.
 * 
 * Return the latest valid time you can get from time by replacing the hidden
 * digits.
 *
 */
public class Solution1736 {

	public String maximumTime(String time) {
		char[] hours = new char[2];
		hours[0] = time.charAt(0);
		hours[1] = time.charAt(1);
		char[] mins = new char[2];
		mins[0] = time.charAt(3);
		mins[1] = time.charAt(4);
		if (hours[0] == '?') {
			if (hours[1] == '?') {
				hours[0] = '2';
				hours[1] = '3';
			} else {
				if (hours[1] - '3' <= 0) {
					hours[0] = '2';
				} else {
					hours[0] = '1';
				}
			}

		} else if (hours[1] == '?') {
			if (hours[0] == '2') {
				hours[1] = '3';
			} else {
				hours[1] = '9';
			}
		}

		if (mins[0] == '?') {
			if (mins[1] == '?') {
				mins[0] = '5';
				mins[1] = '9';
			} else {
				mins[0] = '5';
			}
		} else if (mins[1] == '?') {
			mins[1] = '9';
		}
		return String.format("%s%s:%s%s", hours[0], hours[1], mins[0], mins[1]);
	}
}
