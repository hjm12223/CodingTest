package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14501_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] days = new int[N + 1];
		int[] pays = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			pays[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
			if (i + days[i] <= N + 1) {
				dp[i + days[i]] = Math.max(dp[i + days[i]], pays[i] + dp[i]);
			}
		}
		System.out.println(dp[N + 1]);
	}
	/*
	dp[날짜] = 최대 받을 수 있는 돈
	 */
}
