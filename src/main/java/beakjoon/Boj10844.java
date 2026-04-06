package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10844 {
	static int MOD = 1000000000;

	/*
	dp[자릿수][끝이 i 인수] = 계단를 만들 수 있는 경우의 수
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N + 1][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i <= N; i++) {
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
			result += dp[N][i] % MOD;
		}
		System.out.println(result);
	}
}