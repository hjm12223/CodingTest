package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2293 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coins = new int[n + 1];
		for (int i = 1; i <= n; i++)
			coins[i] = Integer.parseInt(br.readLine());

		int[] dp = new int[10001];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= 10000; j++) {
				if (j - coins[i] > 0) {
					dp[j] = dp[j] + dp[j - coins[i]];
				} else if (j - coins[i] == 0) {
					dp[j]++;
				}
			}
		}
		System.out.println(dp[k]);
	}
}
