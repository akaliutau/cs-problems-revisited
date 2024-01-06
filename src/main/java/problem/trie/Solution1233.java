package problem.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 
 * Given a list of folders, remove all sub-folders in those folders and return
 * in any order the folders after removing.
 * 
 * If a folder[i] is located within another folder[j], it is called a sub-folder
 * of it.
 * 
 * The format of a path is one or more concatenated strings of the form: /
 * followed by one or more lowercase English letters. For example, /leetcode and
 * /leetcode/problems are valid paths while an empty string and / are not.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"] Output:
 * ["/a","/c/d","/c/f"] Explanation: Folders "/a/b/" is a subfolder of "/a" and
 * "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * 
 * IDEA:
 * 
 * 1. use trie to filter out overlapping paths 
 * 2. sort paths before processing
 * 3. during collecting phase on trie:
 *    if a valid path is found, drop all paths which are lower in hierarchy
 *    
 */
public class Solution1233 {
    class FSNode {
        Map<String,FSNode> children = new HashMap<>();
        boolean containsPath = false;
        
    }
    
    void dfs(FSNode node, Stack<String> path, Set<String> collector){
        if (node == null){
            return;
        }
        String p = "/" + path.stream().collect(Collectors.joining("/"));
        if (!collector.contains(p) && node.containsPath){
            collector.add(p);
            return;
        }
        for (String fsn : node.children.keySet()){
            path.add(fsn);
            dfs(node.children.get(fsn), path, collector);
            path.pop();
        }
    }
    
    public List<String> removeSubfolders(String[] folder) {
        FSNode root = new FSNode();
        Arrays.sort(folder);
        
        for (String f : folder){
            String[] path = f.split("/");
            FSNode node = root;
            for (String part : path){
                if (part.equals("")){
                    continue;
                }
                if (!node.children.containsKey(part)){
                    node.children.put(part, new FSNode());
                }
                node = node.children.get(part);
            }
            node.containsPath = true;
        }
        
        Set<String> lst = new HashSet<>();
        Stack<String> path = new Stack<>();
        dfs(root, path, lst);
        
        return new ArrayList<>(lst);
        
    }


}
