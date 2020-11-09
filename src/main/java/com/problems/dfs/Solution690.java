package com.problems.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a data structure of employee information, which includes the
 * employee's unique id, their importance value and their direct subordinates'
 * id. For example, employee 1 is the leader of employee 2, and employee 2 is
 * the leader of employee 3. They have importance value 15, 10 and 5,
 * respectively. Then employee 1 has a data structure like [1, 15, [2]], and
 * employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that
 * although employee 3 is also a subordinate of employee 1, the relationship is
 * not direct. Now given the employee information of a company, and an employee
 * id, you need to return the total importance value of this employee and all
 * their subordinates.
 */
public class Solution690 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
    
    int dfs(int eid) {
        Employee employee = emap.get(eid);
        int ans = employee.importance;
        for (Integer subid: employee.subordinates) {
            ans += dfs(subid);
        }
        return ans;
    }

    Map<Integer, Employee> emap;
    public int getImportance(List<Employee> employees, int queryid) {
        emap = new HashMap<>();
        for (Employee e: employees) {
        	emap.put(e.id, e);
        }
        return dfs(queryid);
    }
    

}
