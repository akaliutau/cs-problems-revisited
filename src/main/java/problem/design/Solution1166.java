package problem.design;

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
 * IDEA:
 *  use a trie (a tree with miltiple nodes)
 * 
 */
public class Solution1166 {

    static class FileSystem {

        static class FSNode {
            Map<String, FSNode> nodes;
            int val;

            FSNode() {
                nodes = new HashMap<>();
                val = -1;
            }
        }

        FSNode root;

        public FileSystem() {
            root = new FSNode();
        }

        // note this method is not used to create folders in bulk
        public boolean createPath(String path, int value) {
            FSNode node = root;
            String[] paths = path.split("/");
            for (int i = 1; i < paths.length; ++i) {// omit the segment before the first '/'
                String p = paths[i];
                if (!node.nodes.containsKey(p)) {
                    if (i != paths.length - 1) {// parent path doesn't exist
                        return false;
                    } else {
                        node.nodes.put(p, new FSNode());
                    }
                }
                node = node.nodes.get(p);
            }
            if (node.val != -1) {// newly created node must have a val=-1
                return false;
            }
            node.val = value;
            return true;
        }

        public int get(String path) {
            FSNode node = root;
            String[] paths = path.split("/");
            for (int i = 1; i < paths.length; ++i) {
                String p = paths[i];
                if (!node.nodes.containsKey(p)) {
                    return -1;
                }
                node = node.nodes.get(p);
            }
            return node.val;
        }

    }

}
