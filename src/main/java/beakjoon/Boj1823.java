package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1823 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		long[][] dp = new long[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			dp[i][i] = (long)arr[i] * N;
		}
		for (int len = 2; len <= N; len++) {
			for (int l = 1; l + len - 1 <= N; l++) {

				int r = l + len - 1;

				int k = N - (r - l);

				dp[l][r] = Math.max(
					dp[l + 1][r] + (long)arr[l] * k,
					dp[l][r - 1] + (long)arr[r] * k
				);
			}
		}
		System.out.println(dp[1][N]);
	}
	/*
		dp[벼의 왼쪽 idx][벼의 오른쪽 idx]  = l ~ r 째 벼만 남았을때 얻을 수 있는 최대이익

	 */
}
