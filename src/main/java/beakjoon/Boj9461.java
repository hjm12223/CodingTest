package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9461 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long[] dp = new long[101];

			dp[0] = 1;
			dp[1] = 1;
			dp[2] = 1;
			for (int k = 3; k <= 100; k++) {
				dp[k] = dp[k - 3] + dp[k - 2];
			}
			System.out.println(dp[N - 1]);
		}
	}
}
