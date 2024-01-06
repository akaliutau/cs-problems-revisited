package problem.stack;

import java.util.List;
import java.util.Stack;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each
 * function has a unique ID between 0 and n-1.
 * 
 * Function calls are stored in a call stack: when a function call starts, its
 * ID is pushed onto the stack, and when a function call ends, its ID is popped
 * off the stack. The function whose ID is at the top of the stack is the
 * current function being executed. Each time a function starts or ends, we
 * write a log with the ID, whether it started or ended, and the timestamp.
 * 
 * You are given a list logs, where logs[i] represents the ith log message
 * formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For
 * example, "0:start:3" means a function call with function ID 0 started at the
 * beginning of timestamp 3, and "1:end:2" means a function call with function
 * ID 1 ended at the end of timestamp 2. Note that a function can be called
 * multiple times, possibly recursively.
 * 
 * A function's exclusive time is the sum of execution times for all function
 * calls in the program. For example, if a function is called twice, one call
 * executing for 2 time units and another call executing for 1 time unit, the
 * exclusive time is 2 + 1 = 3.
 * 
 * Return the exclusive time of each function in an array, where the value at
 * the ith index represents the exclusive time for the function with ID i.
 * 
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"] Output:
 * [3,4] Explanation: Function 0 starts at the beginning of time 0, then it
 * executes 2 for units of time and reaches the end of time 1. Function 1 starts
 * at the beginning of time 2, executes for 4 units of time, and ends at the end
 * of time 5. Function 0 resumes execution at the beginning of time 6 and
 * executes for 1 unit of time. So function 0 spends 2 + 1 = 3 units of total
 * time executing, and function 1 spends 4 units of total time executing
 * 
 * IDEA:
 * 
 * Processes have the following form:
 * 
 * 0:start:3
 * 0:end:4
 * 1:start:0
 * 1:end:4
 * 
 * 
 *  0   1   2   3   4
 *              -----
 *  -----------------
 *              
 *  1) use stack to put on hold already running processes and to process new ones
 *  2)            
 */
public class Solution636 {
	
	enum Type {START, END}
	
	static class Function {
		String[] s;
		
		public Function(String log) {
			s = log.split(":");
		}
		
		public int getId() {
			return Integer.parseInt(s[0]);
		}

		
		public int getTimestamp() {
			return Integer.parseInt(s[2]);
		}
		
		public Type getType() {
			return s[1].equals("start") ? Type.START : Type.END;
		}
	}

	public int[] exclusiveTime(int n, List<String> logs) {
		Stack<Integer> stack = new Stack<>();// the top always contains the current running function
		int[] res = new int[n];
		Function f1 = new Function(logs.get(0));
		stack.push(f1.getId());
		
		int prev = f1.getTimestamp();
		for (String log : logs) {
			Function f = new Function(log);
			if (f.getType().equals(Type.START)) {
				if (!stack.isEmpty()) {
					res[stack.peek()] += f.getTimestamp() - prev;// updated running time of previous function
				}
				stack.push(f.getId());// take cpu
				prev = f.getTimestamp();// start new exclusive period
			} else {
				res[stack.peek()] += f.getTimestamp() - prev + 1;// updated running time of current function, NOTE: end inclusive so add +1
				stack.pop();// pass control to the prev function if any
				prev = f.getTimestamp() + 1;// set to end + 1
			}
		}
		return res;
	}

}
