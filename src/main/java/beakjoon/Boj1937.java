package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1937 {
	static int N;
	static int[][] arr, dp;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = dfs(i, j);
				result = Math.max(dp[i][j], result);
			}
		}
		System.out.println(result);
	}

	private static int dfs(int x, int y) {
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 1;
		for (int[] move : moves) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if (arr[nx][ny] > arr[x][y]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
			}
		}
		return dp[x][y];
	}
}
