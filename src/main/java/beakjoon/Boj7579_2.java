package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj7579_2 {
	static final int INF = (1 << 30);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] memories = new int[N + 1];
		int[] costs = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			memories[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			costs[i] = Integer.parseInt(st.nextToken());

		int maxCost = 10000;
		int[] dp = new int[maxCost + 1];

		for (int i = 1; i <= N; i++) {
			for (int c = maxCost; c >= costs[i]; c--) {
				dp[c] = Math.max(dp[c], dp[c - costs[i]] + memories[i]);
			}
		}
		for (int c = 0; c <= maxCost; c++) {
			if (dp[c] >= M) {
				System.out.println(c);
				break;
			}
		}
	}
}
