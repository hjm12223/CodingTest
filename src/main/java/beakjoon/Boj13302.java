package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj13302 {
	static int N, M;
	static int[][] dp;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				visited[Integer.parseInt(st.nextToken())] = true;
			}
		}
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(1, 0));
	}

	private static int dfs(int day, int coupon) {
		if (day > N) return 0;
		if (dp[day][coupon] != -1) return dp[day][coupon];

		dp[day][coupon] = Integer.MAX_VALUE;

		if (visited[day]) {
			return dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon));
		} else {
			if (coupon >= 3) {
				dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon - 3));
			}
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 1, coupon) + 10000);
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 3, coupon + 1) + 25000);
			dp[day][coupon] = Math.min(dp[day][coupon], dfs(day + 5, coupon + 2) + 37000);

		}
		return dp[day][coupon];
	}
}
