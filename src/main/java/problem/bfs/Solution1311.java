package problem.bfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * There are n people, each person has a unique id between 0 and n-1. Given the
 * arrays watchedVideos and friends, where watchedVideos[i] and friends[i]
 * contain the list of watched videos and the list of friends respectively for
 * the person with id = i. 
 * 
 * Level 1 of videos are all watched videos by your friends, 
 * level 2 of videos are all watched videos by the friends of your
 * friends and so on.
 * 
 *  In general, the level k of videos are all watched videos
 * by people with the shortest path exactly equal to k with you. 
 * 
 * Given your id
 * and the level of videos, return the list of videos ordered by their
 * frequencies (increasing). For videos with the same frequency order them
 * alphabetically  
 * 
 * IDEA:
 * 
 * 
 */
public class Solution1311 {

    static class Video {
        String title;
        int freq;

        public Video(String title, int freq) {
            this.title = title;
            this.freq = freq;
        }

    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        Map<String, Video> stat = new HashMap<>();

        while (!queue.isEmpty() && level > 0) {
            int layerSize = queue.size();
            for (int k = 0; k < layerSize; k++) {
                int cur = queue.poll();
                for (int i : friends[cur]) {
                    if (visited.contains(i)) {
                        continue;
                    }
                    visited.add(i);
                    queue.add(i);
                }
            }
            level--;
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (String s : watchedVideos.get(cur)) {
                stat.computeIfAbsent(s, title -> new Video(title, 0)).freq++;
            }
        }

        Comparator<Video> byFreq = (a, b) -> Integer.compare(a.freq, b.freq);
        Comparator<Video> byTitle = (a, b) -> a.title.compareTo(b.title);

        PriorityQueue<Video> pq = new PriorityQueue<>(byFreq.thenComparing(byTitle));

        for (Video v : stat.values()) {
            pq.offer(v);
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().title);
        }
        return res;
    }

}
