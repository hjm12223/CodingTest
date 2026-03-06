package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1010 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		System.out.println(T);
		long[][] dp = new long[31][31];

		for (int m = 0; m <= 30; m++) {
			dp[0][m] = 1;
			dp[m][m] = 1;
		}
		for (int n = 1; n <= 30; n++) {
			for (int m = n + 1; m <= 30; m++) {
				dp[n][m] = dp[n][m - 1] + dp[n - 1][m - 1];
			}
		}
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			System.out.println(dp[N][M]);
		}
	}
}
