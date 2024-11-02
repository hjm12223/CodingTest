package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1463_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int[] dp = new int[x + 1];
		int[] pre = new int[x + 1];
		dp[1] = 0;

		for (int i = 2; i <= x; i++) {
			dp[i] = dp[i - 1] + 1;
			pre[i] = i - 1;
			if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
				dp[i] = dp[i / 2] + 1;
				pre[i] = i / 2;
			}
			if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
				dp[i] = dp[i / 3] + 1;
				pre[i] = i / 3;
			}
		}
		int cur = x;
		System.out.println(dp[cur]);
		while (true) {
			System.out.print(cur + " ");
			if (cur == 1) break;
			cur = pre[cur];
		}
	}

}
