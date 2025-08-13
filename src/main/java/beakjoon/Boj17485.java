package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17485 {
	static int[][] moves = new int[][] {{1, 0}, {1, 1}, {1, -1}}; // 하,우하,좌하
	static int[][][] dp;
	static int[][] arr;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int result = Integer.MAX_VALUE;
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N][M][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for (int col = 0; col < M; col++) {
			for (int dir = 0; dir < 3; dir++) {
				int value = dfs(0, col, dir);
				result = Math.min(value, result);
			}
		}
		System.out.println(result);
	}

	private static int dfs(int row, int col, int dir) {
		if (col < 0 || col >= M)
			return Integer.MAX_VALUE;
		if (row == N - 1) {
			return arr[row][col];
		}
		if (dp[row][col][dir] != -1)
			return dp[row][col][dir];

		int minCost = Integer.MAX_VALUE;
		for (int nextDir = 0; nextDir < 3; nextDir++) {
			if (dir == nextDir) continue;
			int nextRow = row + moves[nextDir][0];
			int nextCol = col + moves[nextDir][1];
			if (nextRow >= N || nextCol < 0 || nextCol >= M) continue;
			int cost = dfs(nextRow, nextCol, nextDir);
			if (cost != Integer.MAX_VALUE) {
				minCost = Math.min(cost + arr[row][col], minCost);
			}
		}
		dp[row][col][dir] = minCost;
		return minCost;
	}

}
