package problem.greedy;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given two arrays A and B of equal size, the advantage of A with respect to B
 * is the number of indices i for which A[i] > B[i].
 * 
 * Return any permutation of A that maximizes its advantage with respect to B.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: A = [2,7,11,15], B = [1,10,4,11] Output: [2,11,7,15]
 *
 * IDEA:
 * Only relative order does matter, so one can apply greedy approach here
 * Sort both arrays, and find the 
 * 
 */
public class Solution870 {

	static class Elem {
		int pos;
		int num;

		public Elem(int pos, int num) {
			this.pos = pos;
			this.num = num;
		}

	}

	public int[] advantageCount(int[] a, int[] b) {
		int n = a.length;
		Arrays.sort(a);
		Elem[] barr = new Elem[n];
		for (int i = 0; i < n; i++) {
			barr[i] = new Elem(i, b[i]);
		}
		Arrays.sort(barr, (o, p) -> o.num - p.num);
		boolean[] filled = new boolean[n];
		int idx = 0;
		int[] res = new int[n];
		Stack<Integer> elems = new Stack<>();
		for (int i = 0; i < n; i++) {
			if (a[i] > barr[idx].num) {
				res[barr[idx].pos] = a[i];
				filled[barr[idx].pos] = true;
				idx++;
			} else {
				elems.add(a[i]);
			}
		}
		for (int i = 0; i < n; i++) {
			if (!filled[i]) {
				res[i] = elems.pop();
			}
		}
		return res;
	}
}
