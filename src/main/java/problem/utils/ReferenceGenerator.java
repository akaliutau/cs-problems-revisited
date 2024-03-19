package problem.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class ReferenceGenerator {

	private static final String templ = "%d. [Problem %s](%s) (%s)";
	private static final Path path = Paths.get("./src/");
	private static final String prefix = "Solution";
	
	private static final Set<String> dropRecords = new HashSet<>(Arrays.asList("Database"));
	
	private static final Map<String,String> map = new HashMap<>();
	
	static {
		map.put("Depth-First Search", "DFS");
		map.put("Breadth-First Search", "BFS");
	}

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("list_a.txt");

		List<String> read = Files.readAllLines(path);
		
		List<Path> files = getAllFiles();
		final Map<String,Path> filesMap = new HashMap<>();
		files.stream().filter(p -> p.getFileName().toString().startsWith("Solution")).forEach(p -> {
			String ref = p.getFileName().toString().replace(".java", "");
			filesMap.put(ref.substring(prefix.length()), p);
		});

		Path out = Paths.get("prime_list.md");
		final int[] counter = new int[1];
		final int[] missing = new int[1];
		try (BufferedWriter writer = Files.newBufferedWriter(out, StandardCharsets.UTF_8)) {
			writer.write("Reference index \n");
			writer.write("==================== \n\n");
			read.forEach(line -> {
				String[] parts = line.split(",");
				if (!dropRecords.contains(parts[1].trim())){
					String ref = getRef(filesMap.get(parts[0]));
					if (ref.equals("N/A")) {
						missing[0] ++;
					}
					String outLine = String.format(templ, counter[0], parts[0], ref, getTags(parts[1]));
					try {
						writer.write(outLine + "\n\n");
						counter[0] ++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.out.println("Index generated, " + counter[0] + " records added, " + missing[0] + " missing");
	}
	
	private static String getRef(Path path) {
		if (path == null) {
			return "N/A";
		}
		return path.toString().replace(File.separator, "/");
	}
	
	private static String getTags(String str) {
		StringBuilder sb = new StringBuilder();
		if (str != null) {
			for (char c : str.toCharArray()) {
				if ('A' <= c && c <= 'Z') {
					if (sb.length() != 0 && sb.charAt(sb.length()-1) != '-') {
						sb.append(" ");
					}
				}
				sb.append(c);
			}
		}
		String ret = sb.toString().replace("  ", " ");
		for (String key : map.keySet()) {
			if (ret.contains(key)) {
				ret = ret.replace(key, map.get(key));
			}
		}
		return ret;
	}


	private static List<Path> getAllFiles() {
		List<Path> files = new ArrayList<>();

		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (!attrs.isDirectory()) {
						files.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return files;
	}
}
