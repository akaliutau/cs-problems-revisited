package problem.tree;

import java.util.HashMap;

import problem.model.TreeNode;

/**
 * Given a binary tree, write a function to get the maximum width of the given
 * tree. The maximum width of a tree is the maximum width among all levels. 
 * 
 * The
 * width of one level is defined as the length between the end-nodes (the
 * leftmost and right most NON-NULL nodes in the level, 
 * NOTE: the null nodes between the end-nodes are also counted into the length calculation. 
 * 
 * It is
 * guaranteed that the answer will in the range of 32-bit signed integer.
 * 
 * 
 * Example 1:  Input: 
 *        1           <-- depth = 0, col = 0
 *       / \ 
 *      3   2         <-- depth = 1
 *     / \   \ 
 *    5   3   9       <-- depth = 2
 *    
 *    Output: 4 Explanation: The maximum
 * width existing in the third level with the length 4 (5,3,null,9).
 * 
 * Edge case:
 * 
 *        1           <-- depth = 0, col = 0
 *       / \ 
 *      3   2         <-- depth = 1
 *       \   \ 
 *        3   9       <-- depth = 2
 *       / \ / \
 *      4   67  9
 *      
 *  IDEA:
 *  1) use preorder traversing, the right branch first
 *  2) for each level hold a mapping recursion depth => index of first rightmost elem (collected naturally due to preorder)
 */
public class Solution662 {

    private Integer maxWidth = 0;
    private HashMap<Integer, Integer> firstColIndexTable;// mapping depth => index of col0

    void dfs(TreeNode node, Integer depth, Integer colIndex) {
        if (node == null)
            return;
        // initialize the value, for the first seen colIndex per level
        Integer firstColIndex = firstColIndexTable.computeIfAbsent(depth, k -> colIndex);// needed because of edge case: left branch in left part = null 

        // max width will be just a difference between the very first node and the last one
        maxWidth = Math.max(this.maxWidth, colIndex - firstColIndex + 1);// if firstColIndex=0, for the 2nd tree answer will be incorrect

        // Preorder dfs 
        dfs(node.left, depth + 1, 2 * colIndex);
        dfs(node.right, depth + 1, 2 * colIndex + 1);
    }

    public int widthOfBinaryTree(TreeNode root) {
        // table contains the first colIndex for each level
        this.firstColIndexTable = new HashMap<Integer, Integer>();

        // start from depth = 0, and colIndex = 0
        dfs(root, 0, 0);

        return this.maxWidth;
    }

}
