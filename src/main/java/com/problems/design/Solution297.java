package com.problems.design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.problems.model.TreeNode;

/**
 * Design
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
