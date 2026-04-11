package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14430_2 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};
	static int[][] arr, dp;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], -1);
		}
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	private static int dfs(int x, int y) {
		if (x >= N || y >= M) {
			return 0;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 0;
		int nx = x + 1;
		int ny = y + 1;
		dp[x][y] = Math.max(dfs(x, ny), dfs(nx, y)) + arr[x][y];
		return dp[x][y];
	}
}
