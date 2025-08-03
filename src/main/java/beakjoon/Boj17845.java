package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] priority = new int[K + 1];
		int[] times = new int[K + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int I = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			priority[i] = I;
			times[i] = T;
		}
		int[][] dp = new int[K + 1][N + 1];
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (j >= times[i]) {
					dp[i][j] = Math.max(dp[i - 1][j - times[i]] + priority[i], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[K][N]);
	}
}
