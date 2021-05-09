package problem.design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import problem.model.TreeNode;

/**
 * Design, BST
 */
public class Solution449 {

    public class Codec {

        public void postorder(TreeNode root, List<Integer> nodes) {
            if (root == null)
                return;
            postorder(root.left, nodes);
            postorder(root.right, nodes);
            nodes.add(root.val);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> nodes = new ArrayList<>();
            postorder(root, nodes);
            return nodes.stream().map(i -> i.toString()).collect(Collectors.joining(" "));
        }

        public TreeNode helper(Integer lower, Integer upper, Deque<Integer> nums) {
            if (nums.isEmpty())
                return null;
            int val = nums.getLast();
            if (val < lower || val > upper) {
                return null;
            }

            nums.removeLast();
            TreeNode root = new TreeNode(val);
            root.right = helper(val, upper, nums);
            root.left = helper(lower, val, nums);
            return root;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            Deque<Integer> nums = new ArrayDeque<Integer>();
            for (String s : data.split("\\s+")) {
                nums.add(Integer.valueOf(s));
            }
            return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
        }
    }


}
