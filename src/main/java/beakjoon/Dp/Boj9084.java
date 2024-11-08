package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 동전 가지수

			int[] coins = new int[N + 1]; // 동전을 담을 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 목표 값

			int[][] dp = new int[N + 1][M + 1];

			for (int i = 0; i <= N; i++) {
				dp[i][0] = 1;
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					dp[i][j] = dp[i - 1][j];
					if (j >= coins[i]) {
						dp[i][j] += dp[i][j - coins[i]];
					}
				}
			}

			bw.write(dp[N][M] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
