package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj19622 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] meetings = new int[N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken());
			meetings[i][1] = Integer.parseInt(st.nextToken());
			meetings[i][2] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];
		dp[0] = meetings[0][2];
		if (N > 1) dp[1] = Math.max(meetings[0][2], meetings[1][2]);

		for (int i = 2; i < N; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + meetings[i][2]);
		}
		System.out.println(dp[N - 1]);
	}
}
