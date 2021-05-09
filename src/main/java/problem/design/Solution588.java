package problem.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Design an in-memory file system to simulate the following functions:
 * 
 * ls: Given a path in string format. If it is a file path, return a list that
 * only contains this file's name. If it is a directory path, return the list of
 * file and directory names in this directory. Your output (file and directory
 * names together) should in lexicographic order.
 * 
 * mkdir: Given a directory path that does not exist, you should make a new
 * directory according to the path. If the middle directories in the path don't
 * exist either, you should create them as well. This function has void return
 * type.
 * 
 * addContentToFile: Given a file path and file content in string format. If the
 * file doesn't exist, you need to create that file containing given content. If
 * the file already exists, you need to append given content to original
 * content. This function has void return type.
 * 
 * readContentFromFile: Given a file path, return its content in string format.
 * 
 * 
 * 
 * Example:
 * 
 * Input:
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 * 
 * Output: [null,[],null,null,["a"],"hello"]
 * 
 * 
 */
public class Solution588 {

	class FileSystem {

		abstract class Entry {
			abstract boolean isFile();

			abstract StringBuilder getContent();

			abstract HashMap<String, Entry> getFiles();

			public Entry find(String[] d, int depth) {
				Entry e = this;
				for (int i = 1; i < depth; i++) {
					e = e.getFiles().get(d[i]);
				}
				return e;
			}
		}

		class Dir extends Entry {

			HashMap<String, Entry> files = new HashMap<>();

			@Override
			boolean isFile() {
				return false;
			}

			@Override
			StringBuilder getContent() {
				return null;
			}

			@Override
			public HashMap<String, Entry> getFiles() {
				return files;
			}

		}

		class File extends Entry {
			StringBuilder content = new StringBuilder();

			@Override
			public boolean isFile() {
				return true;
			}

			@Override
			public StringBuilder getContent() {
				return content;
			}

			@Override
			public HashMap<String, Entry> getFiles() {
				return null;
			}

		}

		Entry root;

		public FileSystem() {
			root = new Dir();
		}

		public List<String> ls(String path) {
			Entry t = root;
			List<String> files = new ArrayList<>();
			if (!path.equals("/")) {
				String[] d = path.split("/");
				t = root.find(d, d.length);
				if (t.isFile()) {
					files.add(d[d.length - 1]);
					return files;
				}
			}
			List<String> lst = new ArrayList<>(t.getFiles().keySet());
			Collections.sort(lst);
			return lst;
		}

		public void mkdir(String path) {
			Entry t = root;
			String[] d = path.split("/");
			for (int i = 1; i < d.length; i++) {
				if (!t.getFiles().containsKey(d[i])) {
					t.getFiles().put(d[i], new Dir());
				}
				t = t.getFiles().get(d[i]);
			}
		}

		public void addContentToFile(String filePath, String content) {

			String[] d = filePath.split("/");
			int len = d.length;
			Entry t = root.find(d, len);

			if (!t.getFiles().containsKey(d[len - 1])) {
				t.getFiles().put(d[len - 1], new File());
			}
			t = t.getFiles().get(d[len - 1]);
			t.getContent().append(content);
		}

		public String readContentFromFile(String filePath) {

			String[] d = filePath.split("/");
			Entry t = root.find(d, d.length - 1);
			return t.getFiles().get(d[d.length - 1]).getContent().toString();
		}
	}

	

}
