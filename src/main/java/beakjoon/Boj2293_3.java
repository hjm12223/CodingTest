package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2293_3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] coins = new int[N];

		for (int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(coins);

		int[] dp = new int[M + 1];
		dp[0] = 1;
		for (int i = 0; i < N; i++) {
			int coin = coins[i];
			for (int j = 1; j <= M; j++) {
				if (j - coin > 0) {
					dp[j] = dp[j - coin] + dp[j];
				} else if (j - coin == 0) {
					dp[j]++;
				}
			}
		}
		System.out.println(dp[M]);
	}
}
