package com.problems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a Leaderboard class, which has 3 functions: addScore(playerId, score):
 * Update the leaderboard by adding score to the given player's score. If there
 * is no player with such id in the leaderboard, add him to the leaderboard with
 * the given score. top(K): Return the score sum of the top K players.
 * reset(playerId): Reset the score of the player with the given id to 0 (in
 * other words erase it from the leaderboard). It is guaranteed that the player
 * was added to the leaderboard before calling this function. Initially, the
 * leaderboard is empty.
 * 
 * IDEA:
 * use a sorting by score and fetching the top k
 * 
 */
public class Solution1244 {

    class Node {
        int id;
        int score;

        public Node(int i, int s) {
            id = i;
            score = s;
        }
    }

    class Leaderboard {

        Map<Integer, Node> map;
        List<Node> scores;

        public Leaderboard() {
            map = new HashMap<>();
            scores = new ArrayList<>();
        }

        public void addScore(int playerId, int score) {
            if (map.containsKey(playerId)) {
                map.get(playerId).score += score;
            } else {
                Node node = new Node(playerId, score);
                map.put(playerId, node);
                scores.add(node);
            }
        }

        public int top(int k) {
            Collections.sort(scores, (x, y) -> y.score - x.score);
            int sum = 0;
            for (int i = 0; i < Math.min(k, scores.size()); ++i) {
                sum += scores.get(i).score;
            }
            return sum;
        }

        public void reset(int playerId) {
            map.get(playerId).score = 0;
        }
    }

}
