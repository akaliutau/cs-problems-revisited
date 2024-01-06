package problem.greedy;

/**
 * You are given two strings s1 and s2 of equal length consisting of letters "x"
 * and "y" only. Your task is to make these two strings equal to each other. You
 * can swap any two characters that belong to different strings, which means:
 * swap s1[i] and s2[j]. Return the minimum number of swaps required to make s1
 * and s2 equal, or return -1 if it is impossible to do so. 
 * 
 * Example 1: Input: s1
 * = "xx", s2 = "yy" Output: 1 
 * 
 * Explanation: Swap s1[0] and s2[1], s1 = "yx", s2
 * = "yx".
 * 
 * IDEA:
 * NOTE: we can remove equal pairs
 * 
 * 1. Calculate separately the number of mismatches:
 *   nx: number of x in the 1st str
 *   ny: number of y in the 1st str
 * 2. 
 *   a) NUMBER OF PAIRS must be divisible on 2
 *   b) number of actual flips = (NUMBER OF PAIRS) / 2, because each flip solves 2 discrepancies: 
 *    
 *       We have to take some X from the 1st string and replace it with some Y from the 2nd string, =>
 *       2 discrepancies will be solve in one shot
 *       
 *       The same must be done with Ys in the 1st string
 *       
 *   
 *       M   M       M     M 
 *  s1 - X X Y Y X Y X Y X X 
 *       | | | | | | | | | | 
 *  s2 - X Y Y X Y X X X Y X
 *  
 *  Special case:
 *  
 *  s1 - X Y X Y Y X 
 *       | | | | | |  
 *  s2 - Y X Y X X Y
 *  
 *   S1 - S2 : (Mis-Matched)
 * 1. X - Y  :     3  <-- number of x which can be traded for y
 * 2. Y - X  :     3  <-- number of y which can be traded for x
 * 
 * 
 * 
 */
public class Solution1247 {

	public int minimumSwap(String s1, String s2) {
        int nx = 0, ny = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i))
                continue;
            if(s1.charAt(i) == 'x') {
                nx++;// number of x in the 1st str, also == number of y in the 2nd str
            }else {
                ny++;// number of y in the 1st str, also == number of x in the 2nd str
            }
        }
        if ((nx + ny) % 2 == 1) {// because nx + ny == total number of Xs in BOTH strings
            return -1;
        }
        return nx / 2  + ny / 2 + (nx % 2 == 1 ? 1 : 0);// add +1 to each if odd
    }

}
