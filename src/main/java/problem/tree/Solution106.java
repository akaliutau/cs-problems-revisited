package problem.tree;

import java.util.HashMap;
import java.util.Map;

import problem.model.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree. For example,
 * given inorder = [9,3,15,20,7] postorder = [9,15,7,20,3] Return the following
 * binary tree: 
 * 
 *      3 
 *    /    \ 
 *  9       20 
 *         /   \ 
 *        15    7
 *    
 *  0   1  2 3  4
 *           |
 *         index  
 *         
 *  IDEA (the same as in 105):
 *  1) use info about postorder to traverse tree
 *  2) use inorder info to terminate tree building
 *  3) use mapping nodeId/val => its linear center  
 *    
 */
public class Solution106 {

    int postIdx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode helper(int left, int right) {
        // if there is no elements to construct subtrees
        if (left > right)
            return null;

        // pick up postIdx element as a root
        int val = postorder[postIdx];
        TreeNode root = new TreeNode(val);

        // root splits inorder list
        // into left and right subtrees
        int center = indexMap.get(val);

        // recursion - go to the  next element in PreOrder list
        postIdx--;
        
        // first build right, as postorder list ends on right nodes 
        root.right = helper(center + 1, right);
        
        // build left 
        root.left = helper(left, center - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // start from the last postorder element
        postIdx = postorder.length - 1;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            indexMap.put(val, idx++);
        }
        return helper(0, inorder.length - 1);
    }

}
