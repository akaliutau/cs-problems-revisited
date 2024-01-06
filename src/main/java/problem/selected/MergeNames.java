package problem.selected;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MergeNames {

    public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> set = new HashSet<>(Arrays.asList(names1));
        Collections.addAll(set, names2);
        String[] ans = new String[set.size()];
        int idx = 0;
        for (String name : set){
            ans[idx++] = name;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] names1 = new String[] {"Ava", "Emma", "Olivia"};
        String[] names2 = new String[] {"Olivia", "Sophia", "Emma"};
        System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma, Olivia, Sophia
    }
}
