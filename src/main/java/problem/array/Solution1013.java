package problem.array;

/**
 * Given an array of integers arr, return true if we can partition the array
 * into three non-empty parts with equal sums.
 * 
 * Formally, we can partition the array if we can find indexes i + 1 < j with
 * (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1]
 * == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1] Output: true Explanation: 0 + 2 + 1 =
 * -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 *
 *  IDEA:
 *  similar to Dutch flag, but for sums:
 *  1) calc total, must be divisible by 3
 *  2) try to find all parts with sum == total / 3 - must be 3 such partitions
 *  
 */
public class Solution1013 {
	public boolean canThreePartsEqualSum(int[] arr) {
		int n = arr.length;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + arr[i];
		}
		if (sum % 3 != 0)
			return false;

		sum = sum / 3;
		int partition = 0;
		int currentSum = 0;

		for (int i = 0; i < n; i++) {
			currentSum = currentSum + arr[i];
			if (currentSum == sum && partition < 3) {
				partition++;
				currentSum = 0;
			}
		}
		return (partition == 3);
	}
}
