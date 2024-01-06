package problem.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the
 * elements are emails representing emails of the account.
 * 
 * Now, we would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some common email to both accounts. Note that
 * even if two accounts have the same name, they may belong to different people
 * as people could have the same name. A person can have any number of accounts
 * initially, but all of their accounts definitely have the same name.
 * 
 * After merging the accounts, return the accounts in the following format: the
 * first element of each account is the name, and the rest of the elements are
 * emails in sorted order. The accounts themselves can be returned in any order.
 * 
 * 
 * 
 * Input: accounts = [ ["John", "johnsmith@mail.com", "john00@mail.com"],
 * ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 
 * Output: [ ["John", "john00@mail.com", "john_newyork@mail.com",
 * "johnsmith@mail.com"], ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]]
 * 
 * Explanation: The first and third John's are the same person as they have the
 * common email "johnsmith@mail.com". The second John and Mary are different
 * people as none of their email addresses are used by other accounts.
 * 
 * IDEA: 
 * 1. we have: named sets, some of them are intersecting
 * 2. create a mapping email => name_set
 * 3. for each account:
 *   -union the 1st email with all the rest on the list
 *    (as a result union operation will make the 1st elem the parent of all emails)
 * 4. group all elements (which we have just added to the set) by parent's id
 * 5. restore the name_set using mapping created earlier (note: it has the excessive amount of entries)
 *   
 * 
 * 
 */
public class Solution721 {

	static class Graph {
		int[] parent;

		public Graph() {
			parent = new int[10001];
			for (int i = 0; i <= 10000; ++i)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Graph g = new Graph();
		Map<String, String> emailToName = new HashMap<>();// needed to restore name
		Map<String, Integer> emailToID = new HashMap<>();// replace unique string with unique int id
		int uuid = 0;
		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				emailToName.put(email, name);
				if (!emailToID.containsKey(email)) {
					emailToID.put(email, uuid++);
				}
				g.union(emailToID.get(account.get(1)), emailToID.get(email));// if sets intersected, then union them
			}
		}

		Map<Integer, List<String>> grouppedByParent = new HashMap<>();// map parent id => all emails in set
		for (String email : emailToName.keySet()) {
			int index = g.find(emailToID.get(email));
			grouppedByParent.computeIfAbsent(index, x -> new ArrayList<>()).add(email);// group by parent of set
		}
		for (List<String> component : grouppedByParent.values()) {
			Collections.sort(component);
			component.add(0, emailToName.get(component.get(0)));// sort and attach name at pos=0
		}
		return new ArrayList<>(grouppedByParent.values());
	}

}
