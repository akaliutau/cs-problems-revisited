package problem.stack;

import java.util.Stack;

/**
 * 
 * Given an absolute path for a file (Unix-style), simplify it. Or in other
 * words, convert it to the canonical path.
 * 
 * In a UNIX-style file system, a period . refers to the current dir.
 * Furthermore, a double period .. moves the dir up a level.
 * 
 * Note that the returned canonical path must always begin with a slash /, and
 * there must be only a single slash / between two dir names. The last
 * dir name (if it exists) must not end with a trailing /. Also, the
 * canonical path must be the shortest string representing the absolute path.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "/home/" Output: "/home" Explanation: Note that there is no trailing
 * slash after the last dir name.
 * 
 * IDEA:
 * Straightforward: use '.' or '..' sequence names as COMMANDS
 * 
 */
public class Solution71 {

	public String simplifyPath(String path) {
	       // Handle empty string
        if (path.isEmpty()) {
            return path;
        }
        
        // Initialize a stack
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        
        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String dir : parts) {
            
            // A no-op for a "." or an empty string
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            } else if (dir.equals("..")) {
                // we pop an entry from the stack if it's non-empty
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Finally, a legitimate dir name, so we add it
                // to our stack
                stack.add(dir);
            }
        }
        
        // Stich together all the dir names together    
        StringBuilder result = new StringBuilder();
        for (String dir : stack) {
            result.append("/");
            result.append(dir);
        }
        
        return result.length() > 0 ? result.toString() : "/" ;
    }



}
