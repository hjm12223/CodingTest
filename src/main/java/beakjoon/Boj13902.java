package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj13902 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] wack = new int[M + 1];

		st = new StringTokenizer(br.readLine());
		Set<Integer> list = new HashSet<>();

		for (int i = 1; i <= M; i++) {
			wack[i] = Integer.parseInt(st.nextToken());
			list.add(wack[i]);
		}

		for (int i = 1; i <= M; i++) {
			for (int j = i + 1; j <= M; j++) {
				int sum = wack[i] + wack[j];
				if (sum <= N) {
					list.add(sum);
				}
			}
		}
		int[] dp = new int[N + 1];
		Arrays.fill(dp, (1 << 30));
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			for (int canWack : list) {
				if (i - canWack >= 0 && dp[i - canWack] != (1 << 30)) {
					dp[i] = Math.min(dp[i], dp[i - canWack] + 1);
				}
			}
		}
		System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
	}
}