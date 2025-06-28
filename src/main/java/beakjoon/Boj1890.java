package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1890 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] arr;
	static long[][] dp;
	static int N;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		dp = new long[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		long result = dfs(0, 0);
		System.out.println(result);
	}

	private static long dfs(int x, int y) {
		if (x == N - 1 && y == N - 1) {
			return 1;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		int jump = arr[x][y];

		if (x + jump < N) dp[x][y] += dfs(x + jump, y);
		if (y + jump < N) dp[x][y] += dfs(x, y + jump);

		return dp[x][y];
	}
}
