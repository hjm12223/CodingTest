package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2579_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] s = new int[N + 1];
		for (int i = 1; i < s.length; i++) {
			st = new StringTokenizer(br.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			if (N == 1) {
				System.out.println(s[i]);
				return;
			}
		}
		int[][] dp = new int[N + 1][3];
		dp[1][1] = s[1];
		dp[1][2] = 0;
		dp[2][1] = s[2];
		dp[2][2] = s[1] + s[2];
		for (int k = 3; k < dp.length; k++) {
			dp[k][1] = Math.max(dp[k - 2][1], dp[k - 2][2]) + s[k];
			dp[k][2] = dp[k - 1][1] + s[k];
		}
		System.out.println(Math.max(dp[N][1], dp[N][2]));
	}
	/*
	계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
	연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
	마지막 도착 계단은 반드시 밟아야 한다.
	따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다.
	하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나,
	첫 번째, 두 번째, 세 번째 계단을 연속해서 모두 밟을 수는 없다.
	 */
}

