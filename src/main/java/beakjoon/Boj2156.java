package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2156 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] grapeWine = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			grapeWine[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[3][N + 1];
		// dp[i-1] 안마시는 경우
		// dp[i-2] + arr[i] 한잔 마시는 경우
		// dp[i-3] + arr[i-1] + arr[i] 두잔 마시는 경우
		dp[0][1] = grapeWine[1];

		for (int i = 1; i <= N; i++) {
			dp[0][i] = Math.max(dp[2][i - 1], dp[1][i - 1]);
			dp[1][i] = dp[0][i - 1] + grapeWine[i];
			dp[2][i] = dp[1][i - 1] + grapeWine[i];
		}

		System.out.println(Arrays.deepToString(dp));
	}
}
