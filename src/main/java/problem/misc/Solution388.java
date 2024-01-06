package problem.misc;

import java.util.Stack;

/**
 * We will represent the file system as a string where "\n\t" mean a
 * subdirectory of the main directory, "\n\t\t" means a subdirectory of the
 * subdirectory of the main directory and so on. Each folder will be represented
 * as a string of letters and/or digits. Each file will be in the form "s1.s2"
 * where s1 and s2 are strings of letters and/or digits. For example, the file
 * system above is represented as
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
 * Given a string input representing the file system in the explained format,
 * return the length of the longest absolute path to a file in the abstracted
 * file system. If there is no file in the system, return 0. Input: input =
 * "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" Output: 20 Explanation: We have
 * only one file and its path is "dir/subdir2/file.ext" of length 20. The path
 * "dir/subdir1" doesn't contain any files.
 * 
 * IDEA:
 * use the stack to build up the paths,
 * and track the longest path as a peak of load of stack
 * 
 */
public class Solution388 {

	class Entry {
        int level;
        String name;
        int len;
        
        public Entry(int level, String name, int len){
            this.level = level;
            this.name = name;
            this.len = len + name.length();
            if (len != 0){
                this.len ++;
            }
        }
        
        public boolean isFile(){
            return name.contains(".");
        }
    }
    public int lengthLongestPath(String input) {
        if (input == null){
            return 0;
        }
        String[] in = input.split("\n");

        Stack<Entry> stack = new Stack<>();
        int maxLen = 0;
        for (String entry : in){
            int n = entry.length();
            int i = 0;
            while(i < n && entry.charAt(i) == '\t'){
                i ++;
            }
            while (!stack.isEmpty() && stack.peek().level >= i){
                stack.pop();
            }
            int len = stack.isEmpty() ? 0 : stack.peek().len;
            stack.add(new Entry(i, entry.substring(i), len));
            if (stack.peek().isFile())
                maxLen = Math.max(maxLen, stack.peek().len);
        }
        
        return maxLen;
        
    }
}
