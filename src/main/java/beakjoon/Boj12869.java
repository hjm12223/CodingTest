package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12869 {
	static int[] attacks = new int[] {9, 3, 1};
	static int[] scv;
	static int N;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		scv = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0);
	}

	private static void dfs(int cnt) {
		if (check()) {
			result = Math.min(cnt, result);
			return;
		}
		for (int i = 0; i < N; i++) {
		}
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			if (scv[i] > 0) return false;
		}
		return true;
	}
}
