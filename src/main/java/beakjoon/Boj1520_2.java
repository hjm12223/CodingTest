package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1520_2 {
	static int N, M;
	static int[][] moves = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N][M];
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(dp(arr, 0, 0));

	}

	private static int dp(int[][] arr, int x, int y) {
		if (x == N - 1 && y == M - 1) return 1;
		if (dp[x][y] != -1) return dp[x][y];

		dp[x][y] = 0;

		for (int[] move : moves) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if (arr[nx][ny] < arr[x][y]) {
				dp[x][y] += dp(arr, nx, ny);
			}
		}
		return dp[x][y];
	}
}
