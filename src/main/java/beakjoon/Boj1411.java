package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Boj1411 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[] pattern = new String[N];
		for (int i = 0; i < N; i++) {
			pattern[i] = make(br.readLine().toCharArray());
		}
		int result = 0;
		for (int i = 0; i < pattern.length; i++) {
			for (int j = i + 1; j < pattern.length; j++) {
				if (pattern[i].equals(pattern[j])) {
					result++;
				}
			}
		}
		System.out.println(result);
	}

	private static String make(char[] arr) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = new TreeMap<>();
		int idx = 0;
		for (char c : arr) {
			if (!map.containsKey(c)) {
				map.put(c, idx++);
			}
			sb.append(map.get(c));
		}
		return sb.toString();
	}
}
