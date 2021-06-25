package problem.string;

/**
 * 
 * Given two version numbers, version1 and version2, compare them.
 * 
 * Version numbers consist of one or more revisions joined by a dot '.'. Each
 * revision consists of digits and may contain leading zeros. Every revision
 * contains at least one character. Revisions are 0-indexed from left to right,
 * with the leftmost revision being revision 0, the next revision being revision
 * 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 * 
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal. If a version number
 * does not specify a revision at an index, then treat the revision as 0. For
 * example, version 1.0 is less than version 1.1 because their revision 0s are
 * the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 * 
 * Return the following:
 * 
 * If version1 < version2, return -1. If version1 > version2, return 1.
 * Otherwise, return 0.
 * 
 * IDEA:
 * 1) compare digit by digit, sequentially
 * 
 * 
 */
public class Solution165 {

	static class Version implements Comparable<Version> {
		public int[] version;

		public Version(String ver) {
			String[] parts = ver.split("\\.");
			version = new int[parts.length];
			for (int i = 0; i < parts.length; i++) {
				version[i] = Integer.valueOf(parts[i]);
			}
		}

		@Override
		public int compareTo(Version other) {
			int minLen = Math.min(version.length, other.version.length);
			for (int i = 0; i < minLen; i++) {
				if (version[i] > other.version[i]) {
					return 1;
				} else if (version[i] < other.version[i]) {
					return -1;
				}
			}
			// if we are here, all versions are equal so far
			// if still equal check the tail
			if (version.length < other.version.length) {
				if (isNotZeroPart(other.version, version.length)) {
					return -1;
				}
			} else {
				if (isNotZeroPart(version, other.version.length)) {
					return 1;
				}

			}
			return 0;
		}

		static boolean isNotZeroPart(int[] vers, int from) {
			for (int i = from; i < vers.length; i++) {
				if (vers[i] > 0) {
					return true;
				}
			}
			return false;
		}
	}

	public int compareVersion(String version1, String version2) {
		Version v1 = new Version(version1);
		Version v2 = new Version(version2);

		return v1.compareTo(v2);
	}

	
}
