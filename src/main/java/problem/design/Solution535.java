package problem.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl and it returns a short URL such
 * as http://tinyurl.com/4e9iAk.
 * 
 * Design the encode and decode methods for the TinyURL service. There is no
 * restriction on how your encode/decode algorithm should work. You just need to
 * ensure that a URL can be encoded to a tiny URL and the tiny URL can be
 * decoded to the original URL.
 * 
 * IDEA:
 * 1. us a map random_int => url to hold the short url
 * 2. cycle generator with check
 * 
 * 
 */
public class Solution535 {

	public class Codec1 {

		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Map<String, String> map = new HashMap<>();
		int count = 1;

		public String getString() {
			int c = count;
			StringBuilder sb = new StringBuilder();
			while (c > 0) {
				c--;
				sb.append(chars.charAt(c % 62));
				c /= 62;
			}
			return sb.toString();
		}

		public String encode(String longUrl) {
			String key = getString();
			map.put(key, longUrl);
			count++;
			return "http://tinyurl.com/" + key;
		}

		public String decode(String shortUrl) {
			return map.get(shortUrl.replace("http://tinyurl.com/", ""));
		}
	}

	public class Codec {
		Map<Integer, String> map = new HashMap<>();
		Random r = new Random();
		int key = r.nextInt(Integer.MAX_VALUE);

		public String encode(String longUrl) {
			while (map.containsKey(key)) {// find unique key
				key = r.nextInt(Integer.MAX_VALUE);
			}
			map.put(key, longUrl);
			return "http://tinyurl.com/" + key;
		}

		public String decode(String shortUrl) {
			return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
		}
	}



}
