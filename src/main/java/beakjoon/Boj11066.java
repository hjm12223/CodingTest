package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj11066 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[][] dp = new int[N + 1][N + 1];
			int[] prefix = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				prefix[i] = prefix[i - 1] + arr[i - 1];
			}
			for (int len = 2; len <= N; len++) {
				for (int i = 1; i <= N - len + 1; i++) {
					int j = i + len - 1;
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i - 1]);
					}
				}
			}
			bw.write(dp[1][N] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
