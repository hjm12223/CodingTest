package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9465_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][N + 2];
			int[][] dp = new int[2][N + 2];

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 2; j < N + 2; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
				dp[i] = arr[i];
			}
			System.out.println("dp = " + Arrays.deepToString(dp));
			/*
			i = 현재 붙일 스티커
			 */
			for (int i = 2; i < N + 2; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
			}
			System.out.println("dp = " + Arrays.deepToString(dp));
		}
	}
	/*
	건너뛰는 경우
	[0, 0, 10, 30, 10, 50, 100, 20, 40]
	[0, 0, 20, 40, 30, 50, 60, 20, 80]

	[0, 0, 10, 50, 70, 200, 340, 580, 910],
	[0, 0, 20, 50, 100, 170, 360, 530, 1020]
	 */
}
