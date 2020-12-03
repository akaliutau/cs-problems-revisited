package com.problems.sort;

import java.util.Arrays;

/**
 * Given an integer array instructions, you are asked to create a sorted array
 * from the elements in instructions. You start with an empty container nums.
 * For each element from left to right in instructions, insert it into nums. The
 * cost of each insertion is the minimum of the following: The number of
 * elements currently in nums that are strictly less than instructions[i]. The
 * number of elements currently in nums that are strictly greater than
 * instructions[i]. For example, if inserting element 3 into nums = [1,2,3,5],
 * the cost of insertion is min(2, 1) (elements 1 and 2 are less than 3, element
 * 5 is greater than 3) and nums will become [1,2,3,3,5]. Return the total cost
 * to insert all elements from instructions into nums. Since the answer may be
 * large, return it modulo 109 + 7
 * 
 *  * Example 1: Input: instructions = [1,5,6,2]
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
 *          6
 *         / \
 *        5   2
 *       /
 *      1  
 * 
 * 2) for (each elem in arr):
 *         
 * 
 * 
 */
public class Solution1649 {

    static class Node {
        int val;
        Node left = null;
        Node right = null;
        int markedNodes = 0;
        int hits = 0;
        
        public Node(int val){
            this.val = val;
        }
    }
    
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
    
    static void findAndMark(Node root, int v){
            if (v < root.val){
                findAndMark(root.left, v);
            }else if (v > root.val){
                findAndMark(root.right, v);
            }else{
                root.hits += 1;
            }
            root.markedNodes += 1;
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
            while (v != curr.val){// go down till node found and calc number of bigger and smaller values
                if (v < curr.val){
                    more += (curr.right != null ? curr.right.markedNodes : 0) + curr.hits;
                    curr = curr.left;
                } else if (v > curr.val){
                    less += (curr.left != null ? curr.left.markedNodes : 0) + curr.hits;
                    curr = curr.right;
                }
            }
            // if curr is not a leaf calc underlying nodes as well
            less += curr.left != null ? curr.left.markedNodes : 0;
            more += curr.right != null ? curr.right.markedNodes : 0;
            cost += Math.min(more, less);
            cost %= mod;
        }
        return (int) cost % mod;

    }
}
