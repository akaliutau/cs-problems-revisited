package problem.heap;

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

    static class Schedule {
        int employee, interval;

        Schedule(int employee, int i) {
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
        Comparator<Schedule> byStart = (a, b) -> avails.get(a.employee).get(a.interval).start - avails.get(b.employee).get(b.interval).start;

        PriorityQueue<Schedule> pq = new PriorityQueue<Schedule>(byStart);
        int emplid = 0;
        int lastEnd = Integer.MAX_VALUE; // global last end across all schs

        for (List<Interval> employee : avails) {// one can add all intervals at once, but more optimal to use only 0 (smaller heap -> faster processing)
            pq.add(new Schedule(emplid++, 0));
            lastEnd = Math.min(lastEnd, employee.get(0).start);// the earliest start must be > lastEnd
        }

        while (!pq.isEmpty()) {
            Schedule sch = pq.poll();
            Interval iv = avails.get(sch.employee).get(sch.interval);
            if (lastEnd < iv.start) {
                free.add(new Interval(lastEnd, iv.start));
            }
            lastEnd = Math.max(lastEnd, iv.end);// shift lastEnd
            
            sch.interval ++;
            if (sch.interval < avails.get(sch.employee).size()) {// get next avail if any for this empl
                pq.add(sch);
            }
        }

        return free;
    }

}
