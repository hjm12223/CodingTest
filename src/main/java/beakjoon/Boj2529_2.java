package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2529_2 {
	static String[] arr;
	static int K;
	static String maxValue = "";
	static String minValue = "";
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		arr = br.readLine().split(" ");
		visited = new boolean[10];
		dfs(0, new StringBuilder());
		System.out.println(maxValue);
		System.out.println(minValue);
	}

	private static void dfs(int depth, StringBuilder sb) {
		if (depth == K + 1) {
			if (check(sb)) {
				String value = sb.toString();
				if (maxValue.isEmpty() || value.compareTo(maxValue) > 0) {
					maxValue = value;
				}
				if (minValue.isEmpty() || value.compareTo(minValue) < 0) {
					minValue = value;
				}
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (!visited[i]) {
				visited[i] = true;
				sb.append(i);
				dfs(depth + 1, sb);
				visited[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	private static boolean check(StringBuilder sb) {
		for (int i = 0; i < K; i++) {
			char a = sb.charAt(i);
			char b = sb.charAt(i + 1);

			if (arr[i].equals(">")) {
				if (a < b) return false;
			} else {
				if (a > b) return false;
			}
		}
		return true;
	}

}
