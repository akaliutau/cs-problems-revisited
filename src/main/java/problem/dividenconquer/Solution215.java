package problem.dividenconquer;

/**
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * Input: [3,2,1,5,6,4] and k = 2 Output: 5
 * 
 * IDEA:
 * use partitioning on order to find chunks of size k
 * [left] 5 [right]
 *           \
 *            size = k-1
 *            
 */
public class Solution215 {

	int[] nums;

	void swap(int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	// Partition the array into 2 halves using a middle
	// The left half will contain all elements greater than the middle
	// The right half will contain all elements smaller than the middle
	// returns number of elems smaller than mid
	int partition(int left, int right) {
		int mid = (left + right) / 2;
		int middle = nums[mid];
		int rank = left;
		
		swap(mid, right);

		for (int i = left; i < right; i++) {
			if (nums[i] > middle) {// if smaller, left in place, else bubble down
				swap(i, rank);
				rank++;
			}
		}

		swap(rank, right);
		// result: array with asymmetric mid @rank 
		// smth like [2,1,3,5,6,7,2] -> 
        // smth like [2,1,3,2,6,7,5] -> 
		// [2,1,3,2, [5], 7,6] NOTE: order distorted
		// return 4 = number of elems < 5
		//                 

		return rank;
	}

	public int findKthLargest(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
        this.nums = nums;

		while (left < right) {
			// Partition the array into 2 halves using a middle
			int partition = partition(left, right);
			// After partition, we know the rank of the middle
			// compare the rank of that middle with k, if equal then we found element
			if (partition == k - 1) {
				return nums[partition];
			}

			if (partition < k - 1) {// shift middle to the right
				left = partition + 1;
			}else {
				right = partition - 1;
			}
		}

		return nums[left];
	}

}
