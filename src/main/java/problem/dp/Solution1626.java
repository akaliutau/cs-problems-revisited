package problem.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you
 * want to choose the team with the highest overall score. The score of the team
 * is the sum of scores of all the players in the team. However, the basketball
 * team is not allowed to have conflicts. A conflict exists if a younger player
 * has a strictly higher score than an older player. A conflict does not occur
 * between players of the same age. Given two lists, scores and ages, where each
 * scores[i] and ages[i] represents the score and age of the ith player,
 * respectively, return the highest overall score of all possible basketball
 * teams. 
 * 
 * Example 1: Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5] Output: 34 
 * Explanation: You can choose all the players. 
 * 
 * Example 2: Input: scores = [4,5,6,5], ages = [2,1,2,1] Output: 16 
 * Explanation: It is best to choose the
 * last 3 players. Notice that you are allowed to choose multiple people of the
 * same age. 
 * 
 * Example 3: Input: scores = [1,2,3,5], ages = [8,9,10,1] Output: 6
 * Explanation: It is best to choose the first 3 players.
 * 
 * IDEA: combination of Circus Tower + LIS
 * 
 */
public class Solution1626 {
    
    static class Player {
        int num = 1;
        int score;
        int age;

        public Player(int score, int age) {
            this.score = score;
            this.age = age;
        }
        
        int getScore() {
            return num * score;
        }
    }
    
    static int key(int score, int age) {
        return 1_000_001 * age + score;
    }
    
    public int bestTeamScore(int[] scores, int[] ages) {
        Map<Integer,Player> players = new HashMap<>();
        int n = scores.length;
        for (int i = 0; i < n; i++) {
            int key = key(scores[i],ages[i]);
            if (players.containsKey(key)) {
                players.get(key).num ++;
            }else {
                players.put(key, new Player(scores[i], ages[i]));
            }
        }
        List<Player> pLi = new ArrayList<>(players.values());
        Comparator<Player> byScore = (o,p) -> o.score - p.score;
        Comparator<Player> byAge = (o,p) -> o.age - p.age;
        pLi.sort(byScore.thenComparing(byAge));
        int totalMaxScore = 0;
        int sz = pLi.size();
        int[] dpTotalScore = new int[sz];// max total score of sequence found so far
        for (int i = 0; i < pLi.size(); i++) {
            Player pl = pLi.get(i);
            int maxScore = 0;
            for (int j = 0; j < i; j++) {
                if (pLi.get(j).age <= pl.age) {// if age constraint allows, try to improve the max total score of sequence
                    maxScore = Math.max(maxScore, dpTotalScore[j]);
                }
            }
            dpTotalScore[i] = maxScore + pl.getScore();// don't forget to include the current team player 
            totalMaxScore = Math.max(totalMaxScore, dpTotalScore[i]);// update of global var
        }
        
        return totalMaxScore;
    }

}
