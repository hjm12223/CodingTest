package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2011 {
	static final int MOD = 1000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		int N = code.length();
		int[] dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			char first = code.charAt(i - 1);
			char second = code.charAt(i - 2);

			if (first >= '1' && first <= '9') {
				dp[i] += dp[i - 1];
				dp[i] = dp[i] % MOD;
			}
			int num = (second - '0') * 10 + (first - '0');
			if (num >= 10 && num <= 26) {
				dp[i] += dp[i - 2];
				dp[i] = dp[i] % MOD;
			}
		}
		System.out.println(dp[N]);
	}
}
