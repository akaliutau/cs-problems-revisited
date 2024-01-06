package problem.math;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * We have an array arr of non-negative integers.
 * 
 * For every (contiguous) subarray 
 * sub = [arr[i], arr[i + 1], ..., arr[j]] (with i <= j), 
 * 
 * we take the bitwise OR of all the elements in sub, obtaining a
 * result arr[i] | arr[i + 1] | ... | arr[j].
 * 
 * Return the number of possible results. Results that occur more than once are
 * only counted once in the final answer
 *
 */
public class Solution898 {

	public int subarrayBitwiseORs(int[] arr) {
		Set<Integer> res = new HashSet<>();
		Set<Integer> prev = new HashSet<>();

		for (int num : arr) {
			Set<Integer> cur = new HashSet<>();
			cur.add(num);// i == j
			for (int prevNum : prev) {
				cur.add(num | prevNum);
			}
			prev = cur;
			// collect the results
			res.addAll(cur);
		}

		return res.size();
	}
}
