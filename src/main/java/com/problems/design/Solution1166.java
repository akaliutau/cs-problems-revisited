package com.problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * You are asked to design a file system that allows you to create new paths and
 * associate them with different values. The format of a path is one or more
 * concatenated strings of the form: / followed by one or more lowercase English
 * letters. For example, "/leetcode" and "/leetcode/problems" are valid paths
 * while an empty string "" and "/" are not. Implement the FileSystem class:
 * 
 * 
 * bool createPath(string path, int value) Creates a new path and associates a
 * value to it if possible and returns true. Returns false if the path already
 * exists or its parent path doesn't exist. 
 * 
 * int get(string path) Returns the
 * value associated with path or returns -1 if the path doesn't exist.
 * 
 */
public class Solution1166 {

    static class FileSystem {

        static class Dir {
            Map<String, Dir> subDirs;
            int val;

            Dir() {
                subDirs = new HashMap<>();
                val = -1;
            }
        }

        Dir root;

        public FileSystem() {
            root = new Dir();
        }

        public boolean createPath(String path, int value) {
            Dir cur = root;
            String[] paths = path.split("/");
            for (int i = 1; i < paths.length; ++i) {
                String p = paths[i];
                if (!cur.subDirs.containsKey(p)) {
                    if (i != paths.length - 1) {
                        return false;
                    } else {
                        cur.subDirs.put(p, new Dir());
                    }
                }
                cur = cur.subDirs.get(p);
            }
            if (cur.val != -1) {
                return false;
            }
            cur.val = value;
            return true;
        }

        public int get(String path) {
            Dir cur = root;
            String[] paths = path.split("/");
            for (int i = 1; i < paths.length; ++i) {
                String p = paths[i];
                if (!cur.subDirs.containsKey(p)) {
                    return -1;
                }
                cur = cur.subDirs.get(p);
            }
            return cur.val;
        }

    }

}
