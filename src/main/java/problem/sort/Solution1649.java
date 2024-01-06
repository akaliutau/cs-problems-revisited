package problem.sort;

import java.util.Arrays;

/**
 * Given an integer array instructions, you are asked to create a sorted array
 * from the elements in instructions. You start with an empty container nums.
 * For each element from left to right in instructions, insert it into nums. 
 * 
 * The cost of each insertion is the minimum of the following: 
 * 
 * 1) The number of elements currently in nums that are strictly less than instructions[i]. 
 * 
 * 2) The number of elements currently in nums that are strictly greater than
 * instructions[i]. 
 * 
 * For example, if inserting element 3 into nums = [1,2,3,5],
 * the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element
 * 5 is greater than 3) and nums will become [1,2,3,3,5]. 
 * 
 * Return the total cost
 * to insert all elements from instructions into nums. Since the answer may be
 * large, return it modulo 109 + 7
 * 
 * Example 1: Input: instructions = [1,5,6,2]
 * Output: 1 
 * 
 * 
 * Explanation: Begin with nums = []. 
 * Insert 1 with cost min(0, 0) = 0, now nums = [1]. 
 * Insert 5 with cost min(1, 0) = 0, now nums = [1,5]. 
 * Insert 6 with cost min(2, 0) = 0, now nums = [1,5,6]. 
 * Insert 2 with cost min(1, 2) = 1, now nums = [1,2,5,6]. 
 * The total cost is 0 + 0 + 0 + 1 = 1.
 * 
 * IDEA 1: https://en.wikipedia.org/wiki/Fenwick_tree
 * IDEA 2:
 * 
 * 1) build a BST for arr=[1,5,6,2]
 * 
 *          5
 *         / \
 *        2   6       <--- if we are traversing BST for elem 2, we have to add all bigger elements at point 5, i.e. right branch
 *       /
 *      1  
 * 
 * 2) for (each elem in arr):
 *    A. find its exact location on the tree O(log n)
 *    B. during traversal mark the path along the way - as a result we will know at any point of time, 
 *    how many times this node has been crossed in the past. Also this information about underlying nodes helps to
 *    figure out number of crossed nodes SMALLER and BIGGER than the current one (due to props of BST)
 * 
 * 	  For example, at the end of full arr processing the node's crossCount will be:
 * 
 *     crossCount | node
 *     -----------------
 *     4             5
 *     2             2
 *     1             1
 *     1             6
 *     
 *     So we can know precisely for 5: exist 2 elems less and 1 elem bigger
 *   
 *  O(n log n) 
 */
public class Solution1649 {

    static class Node {
        int val;
        Node left = null;
        Node right = null;
        int crossCount = 0;
        int hits = 0; // number of coincidences 
        
        public Node(int val){
            this.val = val;
        }
    }
    
    /**
     * Simple method to build BSt from sorted array
     * @param values
     * @param first
     * @param last
     * @return
     */
    static Node buildTree(int[] values, int first, int last){
            if (first > last){
                return null;
            }
            
            int mid = (first + last) / 2;
            Node root = new Node(values[mid]);
            root.left = buildTree(values, first, mid - 1);
            root.right = buildTree(values, mid + 1, last);
            return root;
    }
    
    /**
     * Executed in O(log n) time because of pre-sorting
     * @param root
     * @param v
     */
    static void findAndMark(Node root, int v){
            if (v < root.val){
                findAndMark(root.left, v);
            }else if (v > root.val){
                findAndMark(root.right, v);
            }else{// v == root.val, exit traversing
                root.hits += 1;
            }
            root.crossCount += 1;
    }
    
    public int createSortedArray(int[] instructions) {
        int[] values = instructions.clone();
        Arrays.sort(values);
        Node root = buildTree(values, 0, values.length - 1);

        int mod = 1_000_000_007;
        long cost = 0;
        
        for (int v : instructions){
            findAndMark(root, v);

            Node curr = root;
            int more = 0;
            int less = 0;
            // this loop simulates the value search on BST
            while (v != curr.val){// go down till node found and calc number of bigger and smaller values
                if (v < curr.val){
                    more += (curr.right != null ? curr.right.crossCount : 0) + curr.hits; 
                    // we don't know yet how many elems are smaller, so we calc bigger (to the right) at the moment
                    curr = curr.left;
                    // navigate to the left
                } else if (v > curr.val){
                    less += (curr.left != null ? curr.left.crossCount : 0) + curr.hits;
                    curr = curr.right;
                }
            }
            // if curr is not a leaf calculate underlying nodes as well
            less += curr.left != null ? curr.left.crossCount : 0;
            more += curr.right != null ? curr.right.crossCount : 0;
            
            cost += Math.min(more, less);
            cost %= mod;
        }
        
        return (int) cost % mod;

    }
}
