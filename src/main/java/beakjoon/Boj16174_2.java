package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16174_2 {
	static int[][] arr, dp;
	static int N;

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
		int result = dfs(0, 0);
		System.out.println(result > 0 ? "HaruHaru" : "Hing");
	}

	static int dfs(int x, int y) {
		if (!isValid(x, y)) return 0;
		if (x == N - 1 && y == N - 1) return 1;
		if (dp[x][y] != -1) return dp[x][y];

		dp[x][y] = 0;
		int jump = arr[x][y];

		dp[x][y] += dfs(x + jump, y);
		dp[x][y] += dfs(x, y + jump);

		return dp[x][y];
	}

	private static boolean isValid(int x, int y) {
		return x < N && y < N && x >= 0 && y >= 0;
	}
}
