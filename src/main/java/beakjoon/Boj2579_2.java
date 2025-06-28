package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2579_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[2][N + 1];


		/*

		dp[0][i] = i번째 계단을 한칸을 건너뛰고 밟은 경우 연속해서 두번 올라간 경우

		dp[1][i] = i번째 계단을 연속해서 두칸을 밟은 경우

		 */

		dp[0][1] = 0;
		dp[0][2] = arr[2];

		dp[1][1] = arr[1];
		dp[1][2] = arr[1] + arr[2];

		for (int i = 3; i <= N; i++) {
			dp[0][i] = Math.max(dp[0][i - 2], dp[1][i - 2]) + arr[i];
			dp[1][i] = dp[0][i - 1] + arr[i];
		}
		System.out.println(Arrays.deepToString(dp));
	}
}
