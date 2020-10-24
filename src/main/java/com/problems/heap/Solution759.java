package com.problems.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time
 * for each employee. Each employee has a list of non-overlapping Intervals, and
 * these intervals are in sorted order. Return the list of finite intervals
 * representing common, positive-length free time for all employees, also in
 * sorted order. (Even though we are representing Intervals in the form [x, y],
 * the objects inside are Intervals, not lists or arrays. 
 * For example,
 * schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is
 * not defined). Also, we wouldn't include intervals like [5, 5] in our answer,
 * as they have zero length. 
 * 
 * Example 1: Input: schedule =
 * [
 * [[1,2],[5,6]],
 * [[1,3]],
 * [[4,10]]
 * ] 
 * Output: [[3,4]] 
 * 
 * 1 [1,2]      [5,6]
 * 2 [1,  3]
 * 3          [4,    10]
 *           
 *            
 * 
 * Explanation: There are a
 * total of three employees, and all common free time intervals would be 
 * [-inf, 1], [3, 4], [10, inf]. 
 * 
 * We discard any intervals that contain inf as they
 * aren't finite.
 * 
 * IDEA:
 * 
 * 
 */
public class Solution759 {

    static class Job {
        int employee, interval;

        Job(int employee, int i) {
            this.employee = employee;
            interval = i;
        }
    }

    // Definition for an Interval.
    static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    };

    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        List<Interval> free = new ArrayList<>();
        Comparator<Job> byStart = (a, b) -> avails.get(a.employee).get(a.interval).start - avails.get(b.employee).get(b.interval).start;

        PriorityQueue<Job> pq = new PriorityQueue<Job>(byStart);
        int emplid = 0, left = Integer.MAX_VALUE;

        for (List<Interval> employee : avails) {// one can add all intervals at once, but more optimal to use only 0 (smaller heap -> faster processing)
            pq.add(new Job(emplid++, 0));
            left = Math.min(left, employee.get(0).start);
        }

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            Interval iv = avails.get(job.employee).get(job.interval);
            if (left < iv.start) {
                free.add(new Interval(left, iv.start));
            }
            left = Math.max(left, iv.end);// shift left
            
            job.interval ++;
            if (job.interval < avails.get(job.employee).size()) {// get next avail if any for this empl
                pq.add(job);
            }
        }

        return free;
    }

}
