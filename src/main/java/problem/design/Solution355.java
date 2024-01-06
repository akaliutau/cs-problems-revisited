package problem.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets in
 * the user's news feed.
 * 
 * Implement the Twitter class:
 * 
 * Twitter() Initializes your twitter object.
 * 
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId
 * by the user userId. Each call to this function will be made with a unique
 * tweetId.
 * 
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs
 * in the user's news feed. Each item in the news feed must be posted by users
 * who the user followed or by the user themself. Tweets must be ordered from
 * most recent to least recent.
 * 
 * void follow(int followerId, int followeeId) The user with ID followerId
 * started following the user with ID followeeId.
 * 
 * void unfollow(int followerId, int followeeId) The user with ID followerId
 * started unfollowing the user with ID followeeId.
 * 
 */
public class Solution355 {
	class Twitter {
		
		class Tweet {
			int tweetId;
			long timestamp;
			
			public Tweet(int tweetId, long timestamp) {
				this.tweetId = tweetId;
				this.timestamp = timestamp;
			}
		}
		
		Map<Integer,List<Tweet>> tweets = new HashMap<>();
		Map<Integer,Set<Integer>> followers = new HashMap<>();
		long timestamp = 0l;
		public Twitter() {

		}

		/** Compose a new tweet. */
		public void postTweet(int userId, int tweetId) {
			tweets.computeIfAbsent(userId, id -> new ArrayList<>()).add(new Tweet(tweetId, timestamp++));
		}

		/**
		 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
		 * the news feed must be posted by users who the user followed or by the user
		 * herself. Tweets must be ordered from most recent to least recent.
		 */
		public List<Integer> getNewsFeed(int userId) {
			PriorityQueue<Tweet> pq = new PriorityQueue<>((o,p) -> Long.compare(o.timestamp, p.timestamp));
			addTweetsofUser(userId, pq);
			Set<Integer> curFollowers = followers.get(userId);
			if (curFollowers != null) {
				for (Integer followerId : curFollowers) {
					addTweetsofUser(followerId, pq);
				}
			}
			
			List<Tweet> ans = new ArrayList<>();
			for (Tweet tweet : pq) {
				ans.add(tweet);
			}
            Collections.sort(ans, (o,p) -> Long.compare(p.timestamp, o.timestamp));
			return ans.stream().map(t -> t.tweetId).collect(Collectors.toList());
		}
		
		private void addTweetsofUser(int userId, PriorityQueue<Tweet> pq) {
			List<Tweet> usersTweets = tweets.get(userId);
			if (usersTweets != null) {
				for (Tweet tweet : usersTweets) {
					pq.add(tweet);
					if (pq.size() > 10) {
						pq.poll();
					}
				}
			}
		}

		/**
		 * Follower follows a followee. If the operation is invalid, it should be a
		 * no-op.
		 */
		public void follow(int followerId, int followeeId) {
			followers.computeIfAbsent(followerId, id -> new HashSet<>()).add(followeeId);
		}

		/**
		 * Follower unfollows a followee. If the operation is invalid, it should be a
		 * no-op.
		 */
		public void unfollow(int followerId, int followeeId) {
			Set<Integer> curFollowers = followers.get(followerId);
			if (curFollowers != null) {
				curFollowers.remove(followeeId);
			}
		}	
	}

}
