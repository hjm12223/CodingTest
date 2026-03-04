package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj16195 {
	static final int MOD = 1000000009;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		long[][] dp = new long[10001][100011];

		dp[0][0] = 1;

		for (int i = 1; i <= 10000; i++) {
			for (int j = 1; j <= 10000; j++) {

				if (i - 1 >= 0) {
					dp[i][j] += dp[i - 1][j - 1];
				}
				if (i - 2 >= 0) {
					dp[i][j] += dp[i - 2][j - 1];
				}
				if (i - 3 >= 0) {
					dp[i][j] += dp[i - 3][j - 1];
				}
				dp[i][j] %= MOD;
			}
		}
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			long result = 0;
			for (int i = 1; i <= M; i++) {
				result += dp[N][i];
				result %= MOD;
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();

	}
	/*
	dp[만들어야 하는 수][사용 하는 갯수(1,2,3)] = 해당 수를 나타낼 수 있는 방법의 수
	 */
}
