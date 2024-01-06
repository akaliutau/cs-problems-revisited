package problem.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A kingdom consists of a king, his children, his grandchildren, and so on.
 * Every once in a while, someone in the family dies or a child is born. The
 * kingdom has a well-defined order of inheritance that consists of the king as
 * the first member. Let's define the recursive function Successor(x, curOrder),
 * which given a person x and the inheritance order so far, returns who should
 * be the next person after x in the order of inheritance. Successor(x,
 * curOrder): if x has no children or all of x's children are in curOrder: if x
 * is the king return null else return Successor(x's parent, curOrder) else
 * return x's oldest child who's not in curOrder 
 * 
 * For example, assume we have a
 * kingdom that consists of the king, his children Alice and Bob (Alice is older
 * than Bob), and finally Alice's son Jack. 
 * 
 * In the beginning, curOrder will be ["king"]. 
 * Calling Successor(king, curOrder) will return Alice, so we append to curOrder to get ["king", "Alice"]. 
 * 
 * Calling Successor(Alice, curOrder) will return Jack, so we append to curOrder to get ["king", "Alice", "Jack"].
 * Calling Successor(Jack, curOrder) will return Bob, so we append to curOrder to get ["king", "Alice", "Jack", "Bob"]. 
 * Calling Successor(Bob, curOrder) will return null. Thus the order of inheritance will be ["king", "Alice", "Jack", "Bob"].
 * 
 * IDEA:
 * use static tree and update it with flag isAlive=false if person died (use this flag to build a hierarchy)
 * 
 */
public class Solution1600 {

    class Tree {
        String name;
        List<Tree> children;
        boolean isAlive;

        public Tree(String n) {
            name = n;
            children = new ArrayList<>();
            isAlive = true;
        }
    }

    class ThroneInheritance {
        Tree root;
        Map<String, Tree> parentMap;

        public ThroneInheritance(String kingName) {
            root = new Tree(kingName);
            parentMap = new HashMap<>();
            parentMap.put(kingName, root);
        }
        
        void dfs(Tree node, List<String> res) {
            if (node == null)
                return;

            if (node.isAlive)
                res.add(node.name);

            for (Tree next : node.children) {
                dfs(next, res);
            }
        }

        public void birth(String parentName, String childName) {
            if (!parentMap.containsKey(parentName))
                return;

            Tree parent = parentMap.get(parentName);
            Tree child = new Tree(childName);
            parent.children.add(child);
            parentMap.put(childName, child);
        }

        public void death(String name) {
            if (!parentMap.containsKey(name))
                return;
            Tree node = parentMap.get(name);
            node.isAlive = false;
        }

        public List<String> getInheritanceOrder() {
            List<String> res = new ArrayList<>();
            dfs(root, res);
            return res;
        }

        

    }
}
