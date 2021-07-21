package problem.dp;

/**
 * There are n dominoes in a line, and we place each domino vertically upright.
 * In the beginning, we simultaneously push some of the dominoes either to the
 * left or to the right.
 * 
 * After each second, each domino that is falling to the left pushes the
 * adjacent domino on the left. Similarly, the dominoes falling to the right
 * push their adjacent dominoes standing on the right.
 * 
 * When a vertical domino has dominoes falling on it from both sides, it stays
 * still due to the balance of the forces.
 * 
 * For the purposes of this question, we will consider that a falling domino
 * expends no additional force to a falling or already fallen domino.
 * 
 * You are given a string dominoes representing the initial state where:
 * 
 * dominoes[i] = 'L', if the ith domino has been pushed to the left, dominoes[i]
 * = 'R', if the ith domino has been pushed to the right, and dominoes[i] = '.',
 * if the ith domino has not been pushed. Return a string representing the final
 * state.
 * 
 * Example 1:
 * 
 * Input: dominoes = "RR.L" Output: "RR.L" 
 * 
 * Explanation: The first domino expends no additional force on the second domino.
 * 
 * IDEA1:
 * 
 * straightforward: check all possible states
 * 
 * 1)
 * no changes: [R][L]
 *          [.][.][.]
 *             [.][.]
 *          [R][.][L]
 *               \
 *                current cell
 * 2) drop cases: current cell == L or R
 * 3) update for cell == '.'               
 *                   
 * IDEA2:
 * 
 *                  
 */
public class Solution838 {

	public String pushDominoes(String s) {
        char[] state = s.toCharArray();
        int n = state.length;
        int[] forces = new int[n];

        // Populate forces going from left to right
        int force = 0;
        for (int i = 0; i < n; ++i) {
            if (state[i] == 'R') {
            	force = n;
            }else if (state[i] == 'L') {
            	force = 0;
            }else {
            	force = Math.max(force - 1, 0);
            }
            forces[i] += force;
        }

        // Populate forces going from right to left
        force = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (state[i] == 'L') {
            	force = n;
            }else if (state[i] == 'R') {
            	force = 0;
            }else {
            	force = Math.max(force - 1, 0);
            }
            forces[i] -= force;
        }

        StringBuilder ans = new StringBuilder();
        for (int f: forces) {
            ans.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        }
        return ans.toString();
    }

}
