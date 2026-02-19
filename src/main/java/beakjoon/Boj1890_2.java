package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1890_2 {
	static int N;
	static int[][] arr;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		dp(0, 0);
		System.out.println(Arrays.deepToString(dp));
	}

	static long dp(int x, int y) {
		if (x >= N || y >= N) {
			return 0;
		}
		if (dp[x][y] != Long.MAX_VALUE) {
			return dp[x][y];
		}
		if (x == N - 1 && y == N - 1) {
			return 1;
		}
		dp[x][y] = 0;
		int value = arr[x][y];

		int nx = x + value;
		int ny = y + value;

		dp[x][y] += dp(nx, y);
		dp[x][y] += dp(x, ny);

		return dp[x][y];
	}
}
