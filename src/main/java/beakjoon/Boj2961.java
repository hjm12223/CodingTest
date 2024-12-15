package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2961 {
	static int result = Integer.MAX_VALUE;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 재료의 개수
		int[][] tastes = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tastes[i][0] = Integer.parseInt(st.nextToken());
			tastes[i][1] = Integer.parseInt(st.nextToken());
		}
		dfs(tastes, 1, 0, 0, 0);
		System.out.println(result);
	}

	private static void dfs(int[][] tastes, int multiply, int sum, int depth, int cnt) {
		if (depth == N) {
			if (cnt > 0) {
				result = Math.min(result, Math.abs(multiply - sum));
			}
			return;
		}
		dfs(tastes, multiply * tastes[depth][0], sum + tastes[depth][1], depth + 1, cnt + 1);
		dfs(tastes, multiply, sum, depth + 1, cnt);
	}
}
