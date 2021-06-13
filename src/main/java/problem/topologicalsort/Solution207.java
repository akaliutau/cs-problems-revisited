package problem.topologicalsort;

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
 * 
 * IDEA:
 * Start from courses with 0 dependencies
 * 
 */
public class Solution207 {

    static class Node {
        public Integer dep = 0;
        public List<Integer> courses = new LinkedList<Integer>();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites.length == 0)
            return true; // no cycle could be formed in empty graph.

        // course -> list of next courses
        HashMap<Integer, Node> graph = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[1] -> relation[0]
            Node dep = graph.computeIfAbsent(relation[1], c -> new Node());
            Node courseToTake = graph.computeIfAbsent(relation[0], c -> new Node());
            dep.courses.add(relation[0]);
            courseToTake.dep += 1;
        }

        // We start from courses that have no prerequisites.
        int totalDeps = prerequisites.length;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (Map.Entry<Integer, Node> entry : graph.entrySet()) {
            Node node = entry.getValue();
            if (node.dep == 0) {
                queue.add(entry.getKey());
            }
        }

        int removedEdges = 0;
        while (queue.size() > 0) {
            Integer course = queue.pop();

            for (Integer courseToTake : graph.get(course).courses) {
                Node childNode = graph.get(courseToTake);
                childNode.dep --;
                removedEdges += 1;
                if (childNode.dep == 0) {// add to queue if and only if no dependencies
                    queue.add(courseToTake);
                }
            }
        }

        // if there are still some edges left, then there exist some cycles
        // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
        return removedEdges == totalDeps;
    }

}
