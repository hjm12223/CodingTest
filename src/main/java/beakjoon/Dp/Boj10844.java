package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10844 {
	private static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][10];

		// 초기값 설정
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		// DP 테이블 채우기
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][1] % MOD;
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][8] % MOD;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
				}
			}
		}

		int result = 0;
		for (int i = 0; i <= 9; i++) {
			result = (result + dp[n][i]) % MOD;
		}

		System.out.println(result);
	}
}
