package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj13910 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] wok = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			wok[i] = Integer.parseInt(st.nextToken());
		}

		Set<Integer> wokSet = new HashSet<>();
		for (int i = 0; i < M; i++) {
			wokSet.add(wok[i]);
			for (int j = i + 1; j < M; j++) {
				wokSet.add(wok[i] + wok[j]);
			}
		}
		int INF = N + 1;
		int[] dp = new int[N + 1];
		Arrays.fill(dp, INF);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int w : wokSet) {
				if (i >= w) {
					dp[i] = Math.min(dp[i], dp[i - w] + 1);
				}
			}
		}

		System.out.println(dp[N] == INF ? -1 : dp[N]);
	}
}
