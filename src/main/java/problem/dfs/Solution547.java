package problem.dfs;

/**
 * There are N students in a class. Some of them are friends, while some are
 * not. Their friendship is transitive in nature. For example, if A is a direct
 * friend of B, and B is a direct friend of C, then A is an indirect friend of
 * C. And we defined a friend circle is a group of students who are direct or
 * indirect friends. Given a N*N matrix adj representing the friend relationship
 * between students in the class. If adj[i][j] = 1, then the ith and jth students
 * are direct friends with each other, otherwise not. And you have to output the
 * total number of friend circles among all the students. 
 * 
 * Example 1: Input:
 * [
 * [1,1,0], 
 * [1,1,0], 
 * [0,0,1]] Output: 2 
 * 
 * Explanation:The 0th and 1st students
 * are direct friends, so they are in a friend circle. The 2nd student himself
 * is in a friend circle. So return 2.
 * 
 * IDEA:
 * use transitive feature of dfs to detect and fill out the friend circle - the same as in islands problem
 * 
 */
public class Solution547 {

    void dfs(int[][] adj, boolean[] visited, int i) {
        for (int j = 0; j < adj.length; j++) {
            if (adj[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(adj, visited, j);
            }
        }
    }

    public int findCircleNum(int[][] adj) {
        int n = adj.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i);// build a tree of connections as big as possible
                count++;// will be limited naturally by max=n
            }
        }
        return count;
    }

}
