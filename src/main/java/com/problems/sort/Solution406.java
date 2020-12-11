package com.problems.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given an array of people, people, which are the attributes of some
 * people in a queue (not necessarily in order). Each people[i] = [hi, ki]
 * represents the ith person of height hi with exactly ki other people in front
 * who have a height greater than or equal to hi. Reconstruct and return the
 * queue that is represented by the input array people. The returned queue
 * should be formatted as an array queue, where queue[j] = [hj, kj] is the
 * attributes of the jth person in the queue (queue[0] is the person at the
 * front of the queue). 
 * 
 * Example 1: Input: people =
 * [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] Output:
 * [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] Explanation: Person 0 has height 5 with
 * no other people taller or the same height in front. Person 1 has height 7
 * with no other people taller or the same height in front. Person 2 has height
 * 5 with two persons taller or the same height in front, which is person 0 and
 * 1. Person 3 has height 6 with one person taller or the same height in front,
 * which is person 1. Person 4 has height 4 with four people taller or the same
 * height in front, which are people 0, 1, 2, and 3. Person 5 has height 7 with
 * one person taller or the same height in front, which is person 1. Hence
 * [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue. 
 * 
 * IDEA: 
 * 
 * 1) use sorting to track the shortest person 
 * 2) on each check compare (the number of people already in queue) with (number of needed persons to be ahead) 
 * 3) add the person at optimal position
 * 
 */
public class Solution406 {

    public int[][] reconstructQueue(int[][] people) {
        Comparator<int[]> byHeight = (o, p) -> o[0] - p[0];
        Comparator<int[]> byLen = (o, p) -> o[1] - p[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(byHeight.thenComparing(byLen));
        for (int[] person : people) {
            pq.add(person);
        }
        List<int[]> ans = new ArrayList<>();
        
        Arrays.sort(people, byLen.thenComparing(byHeight));

        int size = 0;

        for (int[] person : people){
            int count = 0;
            int i = 0;
            while (i < size){
                if (ans.get(i)[0] >= person[0]) {// count the number of taller persons ahead
                    count++;
                }
                if (count > person[1]) {// find the pos where the min number is reached
                    break;
                }
                i++;
            }
            if (ans.isEmpty()){
                ans.add(person);
            }else{
                ans.add(i, person);
            }
            size++;
        }

        return ans.toArray(new int[ans.size()][]);
     }

}
