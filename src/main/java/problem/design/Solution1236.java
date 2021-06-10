package problem.design;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given a url startUrl and an interface HtmlParser, implement a web crawler to
 * crawl all links that are under the same hostname as startUrl.
 * 
 * Return all urls obtained by your web crawler in any order.
 * 
 * Your crawler should:
 * 
 * Start from the page: startUrl Call HtmlParser.getUrls(url) to get all urls
 * from a webpage of given url. Do not crawl the same link twice. Explore only
 * the links that are under the same hostname as startUrl.
 * 
 * IDEA:
 * 
 * BFS and filter out all URLs which have the same host names
 *
 */
public class Solution1236 {

	final static String prefix = "http://";

	interface HtmlParser {
		public List<String> getUrls(String url);
	}

	Queue<String> queue = new LinkedList<>();
	
	/*
	 * Extracts the first part of the name
	 */
	static String getHostName(String url) {
		int idx = url.indexOf(prefix);
		if (idx >= 0) {
			url = url.substring(prefix.length());
		}
		String[] parts = url.split("/");
		return parts[0];
	}

	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		queue.add(startUrl);
		String hostUrl = getHostName(startUrl);
		Set<String> res = new HashSet<>();

		while (!queue.isEmpty()) {
			String url = queue.poll();
			res.add(url);
			List<String> urlsInWebPage = htmlParser.getUrls(url);
			urlsInWebPage.forEach(childUrl -> {
				if (getHostName(childUrl).equals(hostUrl) && !res.contains(childUrl)) {// extract host name and compare it with the host
					queue.offer(childUrl);
				}
			});
		}
		return new ArrayList<>(res);
	}


}
