package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj4781 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(st.nextToken());
			int M = (int)Math.round(Double.parseDouble(st.nextToken()) * 100);
			if (N == 0 && M == 0) break;
			int[] cal = new int[N + 1];
			int[] money = new int[N + 1];
			int[][] dp = new int[N + 1][M + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				cal[i] = Integer.parseInt(st.nextToken());
				money[i] = (int)Math.round(Double.parseDouble(st.nextToken()) * 100);
			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					dp[i][j] = dp[i - 1][j];
					if (j >= money[i]) {
						dp[i][j] = Math.max(dp[i - 1][j],
							Math.max(dp[i][j - money[i]] + cal[i], dp[i - 1][j - money[i]] + cal[i])
						);
					}
				}
			}
			/*
			299 3.00
			499 5.00
			 */
			sb.append(dp[N][M] + "\n");
			st = new StringTokenizer(br.readLine());
		}
		System.out.println(sb);
	}
}
