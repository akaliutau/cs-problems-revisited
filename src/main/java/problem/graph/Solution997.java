package problem.graph;

/**
 * In a town, there are N people labeled from 1 to N. There is a rumor that one
 * of these people is secretly the town judge.
 * 
 * If the town judge exists, then:
 * 
 * The town judge trusts nobody. Everybody (except for the town judge) trusts
 * the town judge. There is exactly one person that satisfies properties 1 and
 * 2. You are given trust, an array of pairs trust[i] = [a, b] representing that
 * the person labeled a trusts the person labeled b.
 * 
 * If the town judge exists and can be identified, return the label of the town
 * judge. Otherwise, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: N = 2, trust = [[1,2]] Output: 2
 * 
 * IDEA:
 * treat trust as a value and transfer it
 * 
 * 
 */
public class Solution997 {

	public int findJudge(int n, int[][] trust) {

		if (trust.length < n - 1) {
			return -1;
		}

		int[] trustee = new int[n + 1];// those who receives trust
		int[] truster = new int[n + 1];// those who trust

		for (int[] relation : trust) {
			truster[relation[0]]++;
			trustee[relation[1]]++;
		}

		for (int i = 1; i <= n; i++) {
			if (trustee[i] == n - 1 && truster[i] == 0) {// condition to search: trusts no one and has got all trust from all other peers
				return i;
			}
		}
		return -1;
	}

	

}
