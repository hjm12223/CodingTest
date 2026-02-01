package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] mem = new int[N];
		int[] cost = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int maxCost = 0;
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			maxCost += cost[i];
		}

		int[] dp = new int[maxCost + 1];

		for (int i = 0; i < N; i++) {
			for (int c = maxCost; c >= cost[i]; c--) {
				dp[c] = Math.max(dp[c], dp[c - cost[i]] + mem[i]);
			}
		}
		System.out.println(Arrays.toString(dp));
		for (int c = 0; c <= maxCost; c++) {
			if (dp[c] >= M) {
				System.out.println(c);
				break;
			}
		}
	}
}
