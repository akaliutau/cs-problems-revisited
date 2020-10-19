package com.problems.topologicalsort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses-1. Some courses may have prerequisites, for example to take course
 * 0 you have to first take course 1, which is expressed as a pair: [0,1] Given
 * the total number of courses and a list of prerequisite pairs, is it possible
 * for you to finish all courses? 
 * 
 * Example 1: Input: numCourses = 2,
 * prerequisites = [[1,0]] Output: true Explanation: There are a total of 2
 * courses to take. To take course 1 you should have finished course 0. So it is
 * possible.
 */
public class Solution207 {

    static class Node {
        public Integer in = 0;
        public List<Integer> out = new LinkedList<Integer>();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites.length == 0)
            return true; // no cycle could be formed in empty graph.

        // course -> list of next courses
        HashMap<Integer, Node> graph = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[1] -> relation[0]
            Node prevCourse = graph.computeIfAbsent(relation[1], c -> new Node());
            Node nextCourse = graph.computeIfAbsent(relation[0], c -> new Node());
            prevCourse.out.add(relation[0]);
            nextCourse.in += 1;
        }

        // We start from courses that have no prerequisites.
        int totalDeps = prerequisites.length;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            if (node.in == 0) {
                queue.add(entry.getKey());
            }
        }

        int removedEdges = 0;
        while (queue.size() > 0) {
            Integer course = queue.pop();

            for (Integer nextCourse : graph.get(course).out) {
                Node childNode = graph.get(nextCourse);
                childNode.in -= 1;
                removedEdges += 1;
                if (childNode.in == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        // if there are still some edges left, then there exist some cycles
        // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
        return removedEdges == totalDeps;
    }

}
