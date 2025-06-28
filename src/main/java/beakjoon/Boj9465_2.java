package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9465_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][N + 2];

			for (int i = 0; i < 2; i++) {
				int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j = 2; j <= N + 1; j++) {
					arr[i][j] = array[j - 2];
				}
			}
			int[][] dp = new int[2][N + 2];
			for (int i = 2; i <= N + 1; i++) {
				dp[0][i] = Math.max(Math.max(dp[0][i - 2], dp[1][i - 2]), dp[1][i - 1]) + arr[0][i];
				dp[1][i] = Math.max(Math.max(dp[1][i - 2], dp[0][i - 2]), dp[0][i - 1]) + arr[1][i];
			}
			bw.write(Math.max(dp[0][N + 1], dp[1][N + 1]) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
