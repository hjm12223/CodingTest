package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj12919_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();
		String target = br.readLine();
		StringBuilder sb = new StringBuilder(target);
		if (dfs(sb, start)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static boolean dfs(StringBuilder sb, String target) {
		if (sb.length() == target.length()) {
			return sb.toString().equals(target);
		}
		if (sb.charAt(sb.length() - 1) == 'A') {
			StringBuilder next = new StringBuilder(sb);
			next.deleteCharAt(next.length() - 1);
			if (dfs(next, target)) {
				return true;
			}
		}
		if (sb.charAt(0) == 'B') {
			StringBuilder next = new StringBuilder(sb).reverse();
			next.deleteCharAt(next.length() - 1);
			if (dfs(next, target)) {
				return true;
			}
		}
		return false;
	}
}
