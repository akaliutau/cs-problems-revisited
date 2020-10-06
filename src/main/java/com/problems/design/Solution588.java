package com.problems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class Solution588 {

    class FileSystem {

        abstract class Entry {
            abstract boolean isFile();
            abstract StringBuilder getContent();
            abstract HashMap<String, Entry> getFiles();
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
                int len = d.length;
                for (int i = 1; i < len; i++) {
                    t = t.getFiles().get(d[i]);
                }
                if (t.isFile()) {
                    files.add(d[len - 1]);
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
            Entry t = root;
            String[] d = filePath.split("/");
            int len = d.length;
            for (int i = 1; i < len - 1; i++) {
                t = t.getFiles().get(d[i]);
            }
            if (!t.getFiles().containsKey(d[len - 1])) {
                t.getFiles().put(d[len - 1], new File());
            }
            t = t.getFiles().get(d[len - 1]);
            t.getContent().append(content);
        }

        public String readContentFromFile(String filePath) {
            Entry t = root;
            String[] d = filePath.split("/");
            for (int i = 1; i < d.length - 1; i++) {
                t = t.getFiles().get(d[i]);
            }
            return t.getFiles().get(d[d.length - 1]).getContent().toString();
        }
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
