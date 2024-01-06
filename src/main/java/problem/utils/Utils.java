package problem.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

import problem.model.ListNode;
import problem.model.TreeNode;



/**
 * I/O util methods
 */
public class Utils {

	private static <T> void print(Stream<T> stream) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		stream.forEach(e -> {
			if (sb.length() > 1) {
				sb.append(",");
			}
			sb.append(e);
		});
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public static ListNode loadList(int[] lst) {
		if (lst == null || lst.length == 0) {
			return null;
		}
		ListNode node = new ListNode(lst[0]);
		ListNode curNode = node;
		for (int i = 1; i < lst.length; i++) {
			curNode.next = new ListNode(lst[i]);
			curNode = curNode.next;
		}
		return node;
	}

	
	public static TreeNode loadTree(Integer[] tree) {
		if (tree == null || tree.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(tree[0]);
		int idx = 0;
		int counter = 1;
		Queue<TreeNode> toProcess = new LinkedList<>();
		toProcess.add(root);
		while (++idx < tree.length) {
			TreeNode cur = toProcess.poll();
			if (idx < tree.length && tree[idx] != null) {
				cur.left = new TreeNode(tree[idx]);
				toProcess.add(cur.left);
				counter++;
			}else {
				cur.left = null;
			}
			idx++;
			if (idx < tree.length && tree[idx] != null) {
				cur.right = new TreeNode(tree[idx]);
				toProcess.add(cur.right);
				counter++;
			}else {
				cur.right = null;
			}
		}
		System.out.println("Total "+counter+" node(s) have been added");
		return root;
	}

	public static void print(int[] vector) {
		if (vector != null) {
			print(Arrays.stream(vector).boxed());
		}else {
			System.out.println("vector is null");
		}
	}
	
	public static void print(long[] vector) {
		if (vector != null) {
			print(Arrays.stream(vector).boxed());
		}else {
			System.out.println("vector is null");
		}
		System.out.println();
	}

	
	public static void print(int[][] vector) {
		if (vector == null || vector.length == 0) {
			System.out.println("Empty array");
		}
		for (int i = 0; i < vector.length; i++) {
			print(Arrays.stream(vector[i]).boxed());
		}
		System.out.println();
	}

	public static void print(char[][] vector) {
		for (int i = 0; i < vector.length; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < vector[i].length; j++) {
				sb.append(vector[i][j]);
			}
			System.out.println(sb.toString());
		}
		System.out.println();
	}


	public static void print(String[] vector) {
		if (vector != null) {
			print(Arrays.stream(vector));
		}else {
			System.out.println("vector is null");
		}
	}

	public static void print(char[] vector, int from) {
		int newSize = vector.length - from;
		Character[] newVector = new Character[newSize < 0 ? 0 : newSize];
		for (int i = from; i < vector.length; i++) {
			newVector[i - from] = vector[i];
		}
		print(Arrays.stream(newVector));

	}
	
	public static void print(ListNode lst) {
		if (lst == null) {
			System.out.println("Empty list");
			return;
		}
		Set<ListNode> processed = new HashSet<>();
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		while (lst != null) {
			if (processed.contains(lst)) {
				System.out.println("Cycle detected!");
				//return;
			}
			if (!first) {
				sb.append("->");
			}
			first = false;
			sb.append(lst.val);
			processed.add(lst);
			lst = lst.next;
		}
		System.out.println(sb.toString());
	}


}
