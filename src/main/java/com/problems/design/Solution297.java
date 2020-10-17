package com.problems.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 */
public class Solution297 {

	public class Codec {
		public void serialize(TreeNode root, StringBuilder sb) {
			if (root == null) {
				sb.append("null,");
			} else {
				sb.append(String.valueOf(root.val));
				sb.append(",");
				serialize(root.left, sb);
				serialize(root.right, sb);
			}
		}

		public TreeNode deserialize(List<String> l) {
			if (l.get(0).equals("null")) {
				l.remove(0);
				return null;
			}

			TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
			l.remove(0);
			root.left = deserialize(l);
			root.right = deserialize(l);

			return root;
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			String[] nodes = data.split(",");
			List<String> list = new LinkedList<>(Arrays.asList(nodes));
			return deserialize(list);
		}

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			StringBuilder sb = new StringBuilder();
			serialize(root, sb);
			return sb.toString();
		}
	}

	public static void main(String[] arg) {

		System.out.println();

	}

}
