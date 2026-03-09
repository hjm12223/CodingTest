package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2096_2 {
	static int[][] arr, dp;
	static int[][] moves = new int[][] {{1, 0}, {1, -1}, {1, 1}};
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		dp = new int[N + 1][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			int dp = dfs(0, i);
			max = Math.max(max, dp);
			min = Math.min(min, dp);
		}
		System.out.println(Arrays.deepToString(dp));
		System.out.println(max + " " + min);
	}

	private static int dfs(int col, int row) {
		if (col >= N || col < 0 || row >= 3 || row < 0) return Integer.MAX_VALUE;
		if (dp[col][row] != Integer.MAX_VALUE) {
			return dp[col][row];
		}

		dp[col][row] = 0;

		for (int[] move : moves) {
			int nextCol = col + move[0];
			int nextRow = row + move[1];
			if (nextCol >= N || nextCol < 0 || nextRow >= 3 || nextRow < 0) continue;
			dp[col][row] = dfs(nextCol, nextRow) + arr[nextCol][nextRow];
		}
		return dp[col][row];
	}
}
