package problem.topologicalsort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
 * 1) Start from courses with 0 dependencies = add them to initial queue
 * 2) Then until queue has elements: on each processing - update deps and potentially add new elems to queue
 * 
 */
public class Solution207 {

    static class Course {
        public Integer dep = 0;
        public List<Integer> courses = new LinkedList<Integer>();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites.length == 0)
            return true; // no cycle could be formed in empty graph.

        // course -> list of next courses
        HashMap<Integer, Course> graph = new HashMap<>();

        // build the graph first
        for (int[] relation : prerequisites) {
            // relation[1] -> relation[0]
            Course dep = graph.computeIfAbsent(relation[1], c -> new Course());         // one can precompute this in a separate cycle
            Course courseToTake = graph.computeIfAbsent(relation[0], c -> new Course());
            dep.courses.add(relation[0]);
            courseToTake.dep ++;
        }

        // We start from courses that have no prerequisites.
        int totalDeps = prerequisites.length;
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Course> entry : graph.entrySet()) {
            Course node = entry.getValue();
            if (node.dep == 0) {// we can add this course
                queue.add(entry.getKey());
            }
        }

        int completedCourses = 0;
        while (queue.size() > 0) {
            Integer course = queue.poll();

            for (Integer courseToTake : graph.get(course).courses) {
                Course childCourse = graph.get(courseToTake);
                childCourse.dep --;
                completedCourses ++;
                if (childCourse.dep == 0) {// add to queue if and only if no dependencies left for this course
                    queue.add(courseToTake);
                }
            }
        }

        // if there are still some edges left, then there exist some cycles
        // Due to the dead-lock (dependencies), we cannot remove the cyclic edges
        return completedCourses == totalDeps;
    }

}
