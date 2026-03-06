package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2839_2 {
	static final int INF = (1 << 30);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[5001];

		Arrays.fill(dp, INF);
		dp[0] = 0;
		dp[3] = 1;
		dp[5] = 1;
		for (int i = 6; i <= N; i++) {
			int prev = Math.min(dp[i - 3], dp[i - 5]);
			if (prev != INF)
				dp[i] = prev + 1;
		}
		System.out.println(dp[N] == INF ? -1 : dp[N]);

	}
}
/*

점화식
내가 이 dp 라는 배열을 어떻게 설정한건지
DP[KG] = 봉지의 최소 개수
[3] = 1개
[5] = 1개
DP[키로] = MATH.MIN(DP[I-3], DP[I-5]) + 1

 */